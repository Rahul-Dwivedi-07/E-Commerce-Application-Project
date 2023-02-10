package com.RahulDwivedi.PaymentService.repository;

import com.RahulDwivedi.PaymentService.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//TransactionDetials as the entity
//Long as the type
@Repository
//Repository annotation to make sure it is repository and available in spring radar
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Long> {
    //defining method in Repository layer for a specific purpose
    //defining declaration
    //find by what? - orderId
    //we just need to give the declaration - implementation is by default by Spring Data JPA
    TransactionDetails findByOrderId(long orderId);
}
