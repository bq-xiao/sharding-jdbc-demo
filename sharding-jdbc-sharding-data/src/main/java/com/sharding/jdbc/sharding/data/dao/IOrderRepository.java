package com.sharding.jdbc.sharding.data.dao;

import com.sharding.jdbc.sharding.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {

}
