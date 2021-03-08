package com.sharding.jdbc.sharding.data;

import com.sharding.jdbc.sharding.data.dao.IOrderMapper;
import com.sharding.jdbc.sharding.data.entity.OrderEntity;
import com.sharding.jdbc.sharding.data.service.ShardingDataService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
            orderEntity.setId(i);
            orderEntity.setName(UUID.randomUUID().toString());
            orderEntity.setDate(sdf.format(new Date()));
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
}
