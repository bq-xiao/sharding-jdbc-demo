package com.sharding.jdbc.sharding.data.dao;

import com.sharding.jdbc.sharding.data.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface IOrderMapper {
    void save(OrderEntity orderEntity);

    List<OrderEntity> query(List<OrderEntity> orderEntity);

    List<OrderEntity> joinQuery(List<OrderEntity> orderEntity);

    List<OrderEntity> findAll();
}
