package com.sharding.jdbc.read.write;

import com.sharding.jdbc.read.write.entity.OrderEntity;
import com.sharding.jdbc.read.write.service.ShardingReadWriteService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingReadWriteTest {
    @Autowired
    private ShardingReadWriteService shardingReadWriteService;

    @Test
    public void save() {
        for (int i = 0; i < 100; i++) {
            shardingReadWriteService.save(i);
        }
        System.out.println("save done!");
    }

    @Test
    public void list() {
        List<OrderEntity> list = shardingReadWriteService.list();
        System.out.println(JSONArray.fromObject(list));
        System.out.println("total count:" + list.size());
    }
}
