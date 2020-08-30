package com.walmart.palindrome.palindromesale.service.offer;

import com.walmart.palindrome.palindromesale.db.entity.Product;
import com.walmart.palindrome.palindromesale.service.offer.impl.PalindromeOffer;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PalindromeOfferTest {

    @InjectMocks
    PalindromeOffer offer;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void apply_oneChar_returnFalse(){

        assertFalse(offer.apply("a"));
    }
    @Test
    public void apply_twoChar_returnFalse(){

        assertFalse(offer.apply("aa"));
    }
    @Test
    public void apply_threeCharPalindrome_returnTrue(){

        assertTrue(offer.apply("aba"));
    }
    @Test
    public void apply_threeCharNonPalindrome_returnFalse(){

        assertFalse(offer.apply("aab"));
    }

    @Test
    public void apply_evenCharPalindrome_returnTrue(){

        assertTrue(offer.apply("asdfggfdsa"));
    }
    @Test
    public void apply_evenCharNonPalindrome_returnFalse(){

        assertFalse(offer.apply("asdfghfdsa"));
    }

    @Test
    public void apply_oddCharPalindrome_returnTrue(){

        assertTrue(offer.apply("asdfgfdsa"));
    }
    @Test
    public void apply_oddCharNonPalindrome_returnFalse(){

        assertFalse(offer.apply("asdfgfxsa"));
    }
    @Test
    public void apply_caseInsensitiveCharPalindrome_returnTrue(){

        assertTrue(offer.apply("aSdfgFdsa"));
    }

    @Test
    public void apply_blankInsensitiveCharPalindrome_returnTrue(){

        assertTrue(offer.apply("     aSdfgFdsa"));
    }

    @Test
    public void applyOffer_success_halfPrice(){

        Product foundOne = createProduct(1,1000l);
        Product foundTwo = createProduct(2, 500l);

        List<Product> products = new ArrayList();
        products.add(foundOne);
        products.add(foundTwo);

        List<Product> response = offer.applyOffer(products);

        assertEquals(500l, response.get(0).getPrice(),0);
        assertEquals(250l,response.get(1).getPrice(),0);

    }

    @Test
    public void applyOffer_successRoundUp_halfPrice(){

        Product foundOne = createProduct(1,11l);
        Product foundTwo = createProduct(2, 5l);

        List<Product> products = new ArrayList();
        products.add(foundOne);
        products.add(foundTwo);

        List<Product> response = offer.applyOffer(products);

        assertEquals(6l, response.get(0).getPrice(),0);
        assertEquals(3l,response.get(1).getPrice(),0);

    }

    private Product createProduct(int key, long price) {
        return new Product(new ObjectId(key,key),
                (long)key,
                "Brand",
                "desc",
                "String image",
                price);
    }
}
