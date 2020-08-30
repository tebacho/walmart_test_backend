package com.walmart.palindrome.palindromesale.service.impl;

import com.walmart.palindrome.palindromesale.db.ProductManager;
import com.walmart.palindrome.palindromesale.db.entity.Product;
import com.walmart.palindrome.palindromesale.service.ProductService;
import com.walmart.palindrome.palindromesale.service.offer.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductManager productManager;

    @Autowired
    Offer offer;

    @Override
    public List<Product> getProduct(String key) {

        List<Product> found = productManager.getProducts(key);
        List<Product> exactMatch = found.stream().filter(product -> key.equals(product.getId().toString())).collect(Collectors.toList());
        List<Product> response = exactMatch.size()==1? exactMatch:found;

        if(offer.apply(key)){
            return offer.applyOffer(response);
        }

            return response;
        }
}


