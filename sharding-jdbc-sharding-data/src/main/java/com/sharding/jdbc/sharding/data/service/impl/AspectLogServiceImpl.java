package com.sharding.jdbc.sharding.data.service.impl;

import com.sharding.jdbc.sharding.data.annotation.ShardingData;
import com.sharding.jdbc.sharding.data.annotation.ShardingDataParam;
import com.sharding.jdbc.sharding.data.service.AspectLogService;
import com.sharding.jdbc.sharding.data.service.ShardingDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AspectLogServiceImpl implements AspectLogService {
    @Autowired
    private ShardingDataService shardingDataService;

    @Override
    @ShardingData
    public void echo(@ShardingDataParam("echo") String str) {
        log.info("==========echo========");
    }

    @Override
    public String action(String str) {
        log.info("======call other=======");
        return shardingDataService.other(str);
    }
}
