package com.sharding.jdbc.sharding.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
//@Table(name = "t_sale_order_item")
public class OrderEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "sku_name")
    private String skuName;
    @Column(name = "unit_name")
    private String unitName;
}
