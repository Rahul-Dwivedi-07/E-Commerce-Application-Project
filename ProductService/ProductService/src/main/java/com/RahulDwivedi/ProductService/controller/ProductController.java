package com.RahulDwivedi.ProductService.controller;

import com.RahulDwivedi.ProductService.model.ProductRequest;
import com.RahulDwivedi.ProductService.model.ProductResponse;
import com.RahulDwivedi.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    //from controller layer we call the business layer that's why controller layer needs object of service layer
    @Autowired
    private ProductService productService;

    //return type is ResponseEntity of type Long
    //addProduct will be taking any of the request body in the form of an object...so we need to create a class that can handle that particular response body, that particular json format that we are sending into a class
    //we passed the long
//    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        //call business layer to add all these operations
        //addProduct method will send back product Id
        long productId = productService.addProduct(productRequest);

        //send the data back
        //send Product Id back to the client which we got from the Service
        //created because we created the product
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    //another Api to get the product
    //another method with response entity and pass back the class with all product information
    //we need to send back the product based on the ids provided...so we need to create the model here where we can return the class back...that particular will have all the product information as we are not going to send the entity object back...because entity we work with db or service layer...for sending back to the client we will have a different pojo
    //PathVariable also
    //PathVariable connects to id and store the data in productId variable
//    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
        //data back in ProductResponse format
        ProductResponse productResponse = productService.getProductById(productId);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    //PutMapping because we are updating something
    //path variable because reduce quantity of which particular product
    @PutMapping("/reduceQuantity/{id}")
    //not passing anything back so Void
    //return type void so we won't get any data back
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId,@RequestParam long quantity){
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
