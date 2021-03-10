package com.sharding.jdbc.sharding.data.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.ArrayList;
import java.util.Collection;

// https://shardingsphere.apache.org/document/legacy/4.x/document/cn/manual/sharding-jdbc/usage/hint/#%E6%B7%BB%E5%8A%A0%E5%88%86%E7%89%87%E9%94%AE%E5%80%BC
@Log4j2
public class HintShardingAlgorithmTB implements HintShardingAlgorithm<String> {
    //复合分片
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        // availableTargetNames:["t_order0","t_order1"],
        // shardingValue:{"columnNameAndRangeValuesMap":["t_order0","t_order1"],
        // "columnNameAndShardingValuesMap":{"order_id":[1],"order_name":["test"]},
        // "logicTableName":"t_order"}
        log.info("availableTargetNames:{}, shardingValue:{}", JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));
        return null;
    }

    //单一分片
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        // select * from t_sale_order_item where id=?;
        // availableTargetNames:["t_sale_order_item"],
        // shardingValue:{"columnName":"id","logicTableName":"t_sale_order_item","value":1}
        log.info("availableTargetNames:{}, shardingValue:{}", JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));
        //1.查询关系表，获取order_id
        //2.根据order_id截取年月
        //3.组装目标表 t_sale_order_item_{year}

        return null;
    }

    //添加分片键值
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
        //select * from t_sale_order_item where id=?;

        //availableTargetNames:
        // ["t_sale_order_item_2103","t_sale_order_item_2104","t_sale_order_item_2105","t_sale_order_item_2106"],
        // shardingValue:{
        // "columnName":"",
        // "logicTableName":"t_sale_order_item",
        // "values":["D210408002642"]}
        log.info("availableTargetNames:{}, shardingValue:{}", JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));
        Collection<String> result = new ArrayList<>();
        for (String each : availableTargetNames) {
            for (String value : shardingValue.getValues()) {
                int index = value.indexOf("D");
                String month = value.substring(index + 1, index + 5);
                if (each.endsWith("_" + month)) {
                    result.add(each);
                }
            }
        }
        System.out.println("======" + Thread.currentThread().getName() + "======" + JSON.toJSONString(result) + "------" + JSON.toJSONString(shardingValue.getValues()));
        return result;
    }
}
