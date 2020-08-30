package com.walmart.palindrome.palindromesale.controller;

import com.walmart.palindrome.palindromesale.db.entity.Product;
import com.walmart.palindrome.palindromesale.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SaleTest {

    @Mock
    ProductService productService;

    @InjectMocks
    Sale sale;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProducts_daoThreeProducts_getAllProducts(){

        List<Product> products = mock(List.class);

        when(productService.getProduct("key")).thenReturn(products);

        ResponseEntity<List<Product>> response = sale.getProduct("key");

        assertEquals(products,response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}
