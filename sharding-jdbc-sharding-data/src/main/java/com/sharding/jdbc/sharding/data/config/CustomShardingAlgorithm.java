package com.sharding.jdbc.sharding.data.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

@Slf4j
public class CustomShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        log.info("availableTargetNames:{}, shardingValue:{}", JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));
        String regionCode = shardingValue.getValue();
        return "ds" + regionCode;
    }
}
