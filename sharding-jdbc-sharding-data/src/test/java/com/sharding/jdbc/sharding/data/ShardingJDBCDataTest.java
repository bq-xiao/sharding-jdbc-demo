package com.sharding.jdbc.sharding.data;

import com.sharding.jdbc.sharding.data.entity.OrderEntity;
import com.sharding.jdbc.sharding.data.service.ShardingDataService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCDataTest {
    @Autowired
    private ShardingDataService shardingDataService;

    @Test
    public void save() {
        for (int i = 0; i < 100; i++) {
            shardingDataService.save(i);
        }
        System.out.println("save done!");
    }

    @Test
    public void list() {
        List<OrderEntity> list = shardingDataService.list();
        System.out.println(JSONArray.fromObject(list));
        System.out.println("total count:" + list.size());
    }
}
