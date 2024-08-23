package com.sharding.jdbc.read.write;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Document(indexName = "logstash-gaia_2024.08.23", type = "doc", createIndex = false)
public class Msg {
    private String message;
    private List<String> tags;
    private String source;
}
