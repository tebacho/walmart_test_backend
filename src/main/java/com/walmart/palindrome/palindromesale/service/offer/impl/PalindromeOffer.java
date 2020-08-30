package com.walmart.palindrome.palindromesale.service.offer.impl;

import com.walmart.palindrome.palindromesale.db.entity.Product;
import com.walmart.palindrome.palindromesale.service.offer.Offer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PalindromeOffer implements Offer {

    @Override
    public boolean apply(String key) {

        char[] keyAsArray = key.toLowerCase().trim().toCharArray();
        int arrayLength = keyAsArray.length;

        if(arrayLength <3){
            return false;
        }

        for(int x=0;x<=(arrayLength-1);x++){
            if(keyAsArray[x]!=keyAsArray[arrayLength-1-x]){
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Product> applyOffer(List<Product> productList) {
        return productList.stream().map(oldProd -> new Product(   oldProd.get_id(),
                oldProd.getId(),
                oldProd.getBrand(),
                oldProd.getDescription(),
                oldProd.getImage(),
                (Math.round(oldProd.getPrice()/2.0)))).collect(Collectors.toList());
    }
}
