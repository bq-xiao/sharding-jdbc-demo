package com.sharding.jdbc.read.write;


import com.alibaba.fastjson.JSON;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ElasticSearchTest {
    @Resource
    private ElasticsearchTemplate template;

    @Test
    public void queryForList() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        MatchPhraseQueryBuilder message = QueryBuilders.matchPhraseQuery("message", "error");
        boolQuery.must(message);

        MatchPhraseQueryBuilder tags = QueryBuilders.matchPhraseQuery("tags", "oms-server");
        boolQuery.must(tags);
        LocalDateTime now = LocalDateTime.now();
        RangeQueryBuilder range = QueryBuilders.rangeQuery("@timestamp")
                .gte(now.minusHours(24).toInstant(ZoneOffset.ofHours(8)).toEpochMilli())
                .lte(now.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        range.format("epoch_millis");
        boolQuery.must(range);

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                //.withFields("message")
                .withTypes("doc")
                .withIndices("logstash-gaia_2024.08.23");

        List<Msg> list = template.queryForList(queryBuilder.build(), Msg.class);
        System.out.println(list.size() + "=====resp=====" + JSON.toJSONString(list));
    }
}
