package pl.webd.dawid124.simpleratingservice.products.model;

import org.springframework.util.StringUtils;
import pl.webd.dawid124.simpleratingservice.comments.model.Comment;
import pl.webd.dawid124.simpleratingservice.file.model.Picture;
import pl.webd.dawid124.simpleratingservice.ratings.model.Rating;
import pl.webd.dawid124.simpleratingservice.type.model.Type;

import java.util.List;

public class Product {

    private long id;
    private String name;
    private String descriptions;
    private double price;
    private Type type;
    private String color;
    private String producer;
    private List<Picture> pictures;
    private List<Rating> ratings;
    private List<Comment> comments;


    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean valid() {
        return StringUtils.hasText(name)
                && price > 0
                && type != null;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
