package com.walmart.palindrome.palindromesale.service.offer;

import com.walmart.palindrome.palindromesale.db.entity.Product;

import java.util.List;

public interface Offer {

    boolean apply(String key);

    List<Product> applyOffer(List<Product> offerApplied);

}
