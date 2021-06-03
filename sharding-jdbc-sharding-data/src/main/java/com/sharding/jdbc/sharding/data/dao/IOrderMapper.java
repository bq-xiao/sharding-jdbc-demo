package com.sharding.jdbc.sharding.data.dao;

import com.sharding.jdbc.sharding.data.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface IOrderMapper {
    void save(OrderEntity orderEntity);

    List<OrderEntity> query(List<OrderEntity> orderEntity);

    List<OrderEntity> get(@Param("regionCode") String regionCode);

    Map<String, Object> defaultTable(@Param("id") String id);

    List<OrderEntity> joinQuery(List<OrderEntity> orderEntity);

    List<OrderEntity> findAll();
}
