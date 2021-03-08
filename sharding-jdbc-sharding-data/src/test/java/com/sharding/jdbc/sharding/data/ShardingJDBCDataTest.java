package com.sharding.jdbc.sharding.data;

import com.sharding.jdbc.sharding.data.entity.OrderEntity;
import com.sharding.jdbc.sharding.data.service.ShardingDataService;
import net.sf.json.JSONArray;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCDataTest {
    private static final String SELECT_SQL_WITH_PARAMETER_MARKER = "SELECT * FROM t_order";

    @Autowired
    private ShardingDataService shardingDataService;

    @Autowired
    private ShardingDataSource shardingDataSource;

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

    @Test
    public void assertInitPreparedStatementExecutorWithReplayMethod() throws SQLException {
        try (PreparedStatement preparedStatement = shardingDataSource.getConnection().prepareStatement(SELECT_SQL_WITH_PARAMETER_MARKER)) {
            preparedStatement.executeQuery();
        }
    }
}
