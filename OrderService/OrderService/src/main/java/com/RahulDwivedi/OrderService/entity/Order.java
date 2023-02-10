package com.RahulDwivedi.OrderService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

//to make sure it is an entity
@Entity
//name of the table
@Table(name = "ORDER_DETAILS")
@Data
@AllArgsConstructor
//default constructor
@NoArgsConstructor
//builder pattern for this class
@Builder
public class Order {

    //for each and every entity there should be an unique key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //order id
    private long id;

    //column annotation for each and every field here
    @Column(name = "PRODUCT_ID")
    private long productId;

    @Column(name = "QUANTITY")
    private long quantity;

    @Column(name = "ORDER_DATE")
    private Instant orderDate;

    @Column(name = "STATUS")
    private String orderStatus;

    @Column(name = "TOTAL_AMOUNT")
    private long amount;
}
