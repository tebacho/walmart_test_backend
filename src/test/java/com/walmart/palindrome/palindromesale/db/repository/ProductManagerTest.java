package com.walmart.palindrome.palindromesale.db.repository;

import com.walmart.palindrome.palindromesale.db.ProductManager;
import com.walmart.palindrome.palindromesale.db.ProductManagerImpl;
import com.walmart.palindrome.palindromesale.db.entity.Product;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public class ProductManagerTest {

    @Captor
    ArgumentCaptor<Query> queryArgumentCaptor;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    ProductManagerImpl productManager;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProducts_exist_returnProductList(){

        List<Product> found = new ArrayList<>();
        found.add(createProduct(1));
        when(mongoTemplate.find(ArgumentMatchers.any(),eq(Product.class))).thenReturn(found);
        List<Product> productList = productManager.getProducts("1");

        verify(mongoTemplate,times(1)).find(queryArgumentCaptor.capture(),eq(Product.class));
        Query query = queryArgumentCaptor.getValue();

        assertEquals("Query: { \"$or\" : [{ \"id\" : { \"$regularExpression\" : { \"pattern\" : \"1\", \"options\" : \"\"}}}, { \"brand\" : { \"$regularExpression\" : { \"pattern\" : \"1\", \"options\" : \"\"}}}, { \"description\" : { \"$regularExpression\" : { \"pattern\" : \"1\", \"options\" : \"\"}}}]}, Fields: {}, Sort: {}", query.toString());
    }

    @Test
    public void getProducts_exist2_returnProductList(){

        List<Product> found = new ArrayList<>();
        found.add(createProduct(1));
        when(mongoTemplate.find(ArgumentMatchers.any(),eq(Product.class))).thenReturn(found);
        List<Product> productList = productManager.getProducts("bran");

        verify(mongoTemplate,times(1)).find(queryArgumentCaptor.capture(),eq(Product.class));
        Query query = queryArgumentCaptor.getValue();

        assertEquals("Query: { \"$or\" : [{ \"id\" : { \"$regularExpression\" : { \"pattern\" : \"bran\", \"options\" : \"\"}}}, { \"brand\" : { \"$regularExpression\" : { \"pattern\" : \"bran\", \"options\" : \"\"}}}, { \"description\" : { \"$regularExpression\" : { \"pattern\" : \"bran\", \"options\" : \"\"}}}]}, Fields: {}, Sort: {}", query.toString());
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
