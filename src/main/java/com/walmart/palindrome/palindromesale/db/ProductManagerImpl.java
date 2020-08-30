package com.walmart.palindrome.palindromesale.db;

import com.walmart.palindrome.palindromesale.db.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManagerImpl implements ProductManager{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Product> getProducts(String key) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").regex(toLikeRegex(key)));
        query.addCriteria(Criteria.where("brand").regex(toLikeRegex(key)));
        query.addCriteria(Criteria.where("description").regex(toLikeRegex(key)));

        List<Product> productsList = mongoTemplate.find(query, Product.class);

        return productsList;
    }

    private String toLikeRegex(String source) {
        return source.replaceAll("\\*", ".*");
    }
}
