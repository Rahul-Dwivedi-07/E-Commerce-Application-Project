package com.RahulDwivedi.ProductService.service;

import com.RahulDwivedi.ProductService.entity.Product;
import com.RahulDwivedi.ProductService.exception.ProductServiceCustomException;
import com.RahulDwivedi.ProductService.model.ProductRequest;
import com.RahulDwivedi.ProductService.model.ProductResponse;
import com.RahulDwivedi.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

//@Service insures that spring knows it is a service
@Service
//implementing log in service layer
@Log4j2
public class ProductServiceImpl implements ProductService{
    //In productService we would require object of repository because from our business layer we would interact with our repository, with our db
    //Injection using Autowired annotation
    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        //object of logger created for us and we can use all the methods directly
        //so we do not have to create the object separately
        log.info("Adding Product..");

        //create the object of Product from Product request
        //this is product entity and we can save it in our db
        //use the builder pattern here
        //product Id will be auto generated
        Product product = Product.builder()
                //passing product name as productRequest.getName()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        //save method to pass this product to save to the db
        productRepository.save(product);

        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for productid: {}",productId);

        /*
        Product product = productRepository.findById(productId)
                //RuntimeException is the standard exception, except this we should pass custom exception by creating a custom exception class
                .orElseThrow(() -> new RuntimeException("Product with given id not found"));
         */
        Product product = productRepository.findById(productId)
                //findById will return the optional...if we get the product that's fine otherwise throw the exception
                //we can give any error code below
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));

        //we got the product and there is no exception then we need to convert this product into our ProductResponse object
        //we can use the builder pattern to create the object here or we can use the bean utils as well to create our object
        //we added NoArgs because of this as it has the constructor now
        ProductResponse productResponse = new ProductResponse();
        //the below will work only when there is matching properties
        //copy all the properties from product and the target is productResponse
        //Added on-demand static import for BeanUtils so that the code looks little bit cleaner
        copyProperties(product,productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity,productId);

        Product product
                = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));

        //if not error then we got the product
        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        //if still no error then update the quantity
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity updated Successfully");
    }
}
