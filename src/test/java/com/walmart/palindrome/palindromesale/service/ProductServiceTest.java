package com.walmart.palindrome.palindromesale.service;

import com.walmart.palindrome.palindromesale.db.ProductManager;
import com.walmart.palindrome.palindromesale.db.entity.Product;
import com.walmart.palindrome.palindromesale.service.impl.ProductServiceImpl;
import com.walmart.palindrome.palindromesale.service.offer.Offer;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Captor
    ArgumentCaptor<List<Product>> capturedList;

    @Mock
    ProductManager productManager;

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    Offer offer;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProducts_success_getAllProducts(){

        List<Product> products = mock(List.class);

        when(productManager.getProducts("key")).thenReturn(products);
        when(offer.apply("key")).thenReturn(false);

        List<Product> response = productService.getProduct("key");

        assertEquals(products,response);

    }

    @Test
    public void getProducts_productWithOffers_getOfferProducts(){

        List<Product> products = Mockito.mock(List.class);
        List<Product> offerProducts = Mockito.mock(List.class);

        when(productManager.getProducts("key")).thenReturn(products);
        when(offer.apply("key")).thenReturn(true);
        when(offer.applyOffer(products)).thenReturn(offerProducts);

        List<Product> response = productService.getProduct("key");

        assertEquals(offerProducts,response);

    }

    @Test
    public void getProducts_idFoundNotOffer_getOneProduct(){

        Product sameKeyProduct = createProduct(1);
        List<Product> products = new ArrayList();

        products.add(createProduct(2));
        products.add(sameKeyProduct);
        products.add(createProduct(3));

        when(productManager.getProducts("1")).thenReturn(products);
        when(offer.apply("1")).thenReturn(false);

        List<Product> response = productService.getProduct("1");

        assertEquals(sameKeyProduct,response.get(0));
        assertTrue(response.size()==1);

    }

    @Test
    public void getProducts_idFoundWithOffer_getOneProduct(){

        Product sameKeyProduct = createProduct(1);
        List<Product> products = new ArrayList();
        List<Product> offerProducts = mock(List.class);

        products.add(createProduct(2));
        products.add(sameKeyProduct);
        products.add(createProduct(3));

        when(productManager.getProducts("1")).thenReturn(products);
        when(offer.apply("1")).thenReturn(true);
        when(offer.applyOffer(any(List.class))).thenReturn(offerProducts);

        productService.getProduct("1");

        verify(offer,times(1)).applyOffer(capturedList.capture());
        List<Product> captured = capturedList.getValue();
        assertEquals(sameKeyProduct,captured.get(0));
        assertTrue(captured.size()==1);

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
