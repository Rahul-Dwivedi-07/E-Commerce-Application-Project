package com.RahulDwivedi.ProductService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//to make this entity, annotate this with @Entity
@Entity
//to avoid getters and setters
//all getters, setters, equals, hashcode methods, required field constructors will be by default created for us
@Data
//constructor with all the field
@AllArgsConstructor
//define default constructor as well when we are defining constructor
@NoArgsConstructor
//will give builder pattern implementation for this class
//@Builder annotation is from Lombok
/*with the help of builder pattern we can add properties in the product whenever we are using.
we do not have to worry about calling the entire constructor,if we want to add couple of fields we can do it using the builder pattern as well.*/
@Builder
public class Product {

    @Id
    //annotation how this unique key will be generated - we want product id to be incremented one by one for each and every product being inserted
    @GeneratedValue(strategy = GenerationType.AUTO)
    //automatically generated on the sequence itself
    private long productId;

    //column name in database
    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "QUANTITY")
    private long quantity;
}
