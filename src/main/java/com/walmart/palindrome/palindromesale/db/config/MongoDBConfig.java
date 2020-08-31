package com.walmart.palindrome.palindromesale.db.config;

import com.walmart.palindrome.palindromesale.db.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by ryanez on 26-08-20.
 */
@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
@Configuration
public class MongoDBConfig {


}
