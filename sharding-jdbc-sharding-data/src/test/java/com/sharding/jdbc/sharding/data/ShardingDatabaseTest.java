package com.sharding.jdbc.sharding.data;

import com.sharding.jdbc.sharding.data.dao.IOrderMapper;
import com.sharding.jdbc.sharding.data.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ShardingDatabaseTest {
    @Autowired
    private IOrderMapper iOrderMapper;

    @Test
    public void query() {
        List<OrderEntity> result = iOrderMapper.get("400002");
        //System.out.println(JSONArray.fromObject(result));
        System.out.println("total count:" + result.size());
    }

    @Test
    public void defaultTable() {
        Map<String, Object> result = iOrderMapper.defaultTable("001");
        //System.out.println(JSONArray.fromObject(result));
        System.out.println("total count:" + result.size());
    }
}
