package com.sharding.jdbc.read.write.service;

import com.sharding.jdbc.common.dao.OrderRepository;
import com.sharding.jdbc.common.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ShardingReadWriteService {
    @Autowired
    private OrderRepository orderRepository;

    public void save(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(i);
        orderEntity.setName(UUID.randomUUID().toString());
        orderEntity.setDate(sdf.format(new Date()));
        orderRepository.save(orderEntity);
    }

    public List<OrderEntity> list() {
        return orderRepository.findAll();
    }
}
