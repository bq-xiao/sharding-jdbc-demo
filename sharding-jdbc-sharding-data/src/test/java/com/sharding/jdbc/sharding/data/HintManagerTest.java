package com.sharding.jdbc.sharding.data;

import com.alibaba.fastjson.JSON;
import org.apache.shardingsphere.api.hint.HintManager;

public class HintManagerTest {
    static HintManager hintManager;
    public static void main(String[] args) {
        for (int i = 0; i<10; i++) {
            // 错误
            /**
             * [1]
             * []
             * []
             * []
             * []
             * []
             * []
             * []
             * []
             * []
             */
            if (null != hintManager) {
                hintManager.close();
            }else {
                hintManager = HintManager.getInstance();
            }
            //正确
            /**
             * [1]
             * [1]
             * [1]
             * [1]
             * [1]
             * [1]
             * [1]
             * [1]
             * [1]
             * [1]
             */
//            if (null != hintManager) {
//                hintManager.close();
//            }
//            hintManager = HintManager.getInstance();

            hintManager.addTableShardingValue("test", 1);
            System.out.println(JSON.toJSONString(HintManager.getTableShardingValues("test")));
        }
    }
}
