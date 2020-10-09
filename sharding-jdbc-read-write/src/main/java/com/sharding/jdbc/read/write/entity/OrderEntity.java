package com.sharding.jdbc.read.write.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_order")
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    private int id;
    @Column(name = "order_name")
    private String name;
    @Column(name = "order_date")
    private String date;
}
