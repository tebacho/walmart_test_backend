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
        Criteria criteria = new Criteria();

        Criteria idCriteria = getLongCriteria(key);
        Criteria brandCriteria = Criteria.where("brand").regex(toLikeRegex(key));
        Criteria descCriteria = Criteria.where("description").regex(toLikeRegex(key));

        Query query = new Query(new Criteria().orOperator(idCriteria,brandCriteria,descCriteria));
        List<Product> productsList = mongoTemplate.find(query, Product.class);

        return productsList;
    }

    private Criteria getLongCriteria(String key) {
        try{
            return Criteria.where("id").is(Long.decode(key));
        }catch(NumberFormatException nfe) {

            return new Criteria();
        }
    }

    private String toLikeRegex(String source) {
        return source.replaceAll("\\*", ".*");
    }
}
