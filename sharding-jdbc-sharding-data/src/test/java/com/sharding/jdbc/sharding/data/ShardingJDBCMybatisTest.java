package com.sharding.jdbc.sharding.data;

import com.sharding.jdbc.sharding.data.dao.IOrderMapper;
import com.sharding.jdbc.sharding.data.entity.OrderEntity;
import net.sf.json.JSONArray;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCMybatisTest {
    @Autowired
    private IOrderMapper iOrderMapper;

    @Test
    public void save() {
        for (int i = 0; i < 100; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setId(i + "");
            orderEntity.setSkuName(UUID.randomUUID().toString());
            orderEntity.setUnitName(sdf.format(new Date()));
            iOrderMapper.save(orderEntity);
        }
        System.out.println("save done!");
    }

    @Test
    public void list() {
        List<OrderEntity> list = iOrderMapper.findAll();
        System.out.println(JSONArray.fromObject(list));
        System.out.println("total count:" + list.size());
    }

    @Test
    public void query() {
        List<OrderEntity> list = new ArrayList<>();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId("100000000227934208");
        orderEntity.setSkuName("test");
        list.add(orderEntity);
        HintManager hintManager = HintManager.getInstance();
        hintManager.addTableShardingValue("t_sale_order_item", "D210308002642");
        List<OrderEntity> result = iOrderMapper.query(list);
        System.out.println(JSONArray.fromObject(result));
        System.out.println("total count:" + result.size());
    }

    @Test
    public void batch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String arr[] = {"D210308002641", "D210408002642", "D210508002643", "D210608002644"};
        Random rand = new Random(4);
        for (int i = 0; i < 9; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    //准备完毕……运动员都阻塞在这，等待号令
                    countDownLatch.await();
                    List<OrderEntity> list = new ArrayList<>();
                    OrderEntity orderEntity = new OrderEntity();
                    orderEntity.setId("10000000027310489" + String.valueOf(index));
                    list.add(orderEntity);
                    int j = rand.nextInt(4);
                    HintManager hintManager = HintManager.getInstance();
                    Thread.currentThread().setName("T" + index + "");
                    System.out.println("=====T" + index + "=======" + arr[j]);
                    hintManager.addTableShardingValue("t_sale_order_item", arr[j]);
                    List<OrderEntity> result = iOrderMapper.query(list);
//                    System.out.println(JSONArray.fromObject(result));
//                    System.out.println("total count:" + JSON.toJSONString(result));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(2000);// 裁判准备发令
        countDownLatch.countDown();// 发令枪：执行发令
        Thread.sleep(20000);// 裁判准备发令
    }
}
