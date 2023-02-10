package com.RahulDwivedi.OrderService.repository;

import com.RahulDwivedi.OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Jpa Repository of type order because that's the entity and Id for entity is Long
//Repository in our spring project because we annotated it with Repository
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
