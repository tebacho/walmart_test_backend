package com.walmart.palindrome.palindromesale.db.repository;

import com.walmart.palindrome.palindromesale.db.entity.Product;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static reactor.core.publisher.Mono.when;

public class ProductRepositoryTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProducts_productExists_returnProductos(){

        List<Product> products = new ArrayList();
        products.add(createProduct(1));
        when(mongoTemplate.find(Mockito.any(),Mockito.any())).thenReturn(products);

    }

    private Product createProduct(int key) {
        return new Product(new ObjectId(key,key),
                (long)key,
                "Brand",
                "desc",
                "String image",
                500l);
    }
}
