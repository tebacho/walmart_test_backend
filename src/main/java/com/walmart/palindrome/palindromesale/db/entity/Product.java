package com.walmart.palindrome.palindromesale.db.entity;

import com.querydsl.core.annotations.QueryEntity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@QueryEntity
@Document(collection = "products")
public class Product {

    @Id
    private ObjectId _id;

    private Long id;
    private String brand;
    private String description;
    private String image;
    private Long price;

    public Product(ObjectId _id, Long id, String brand, String description, String image, Long price) {
        this._id = _id;
        this.id = id;
        this.brand = brand;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
