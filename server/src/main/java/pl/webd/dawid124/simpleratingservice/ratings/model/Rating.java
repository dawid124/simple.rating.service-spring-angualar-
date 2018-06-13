package pl.webd.dawid124.simpleratingservice.ratings.model;

import pl.webd.dawid124.simpleratingservice.comments.model.ProductRelation;

import java.util.Date;

public class Rating implements ProductRelation {

    private long id;
    private String descriptions;
    private Date date;
    private int rating;
    private long userId;
    private String username;
    private long productId;

    public Rating() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public long getProductId() {
        return productId;
    }

    @Override
    public void setProductId(long productId) {
        this.productId = productId;
    }

    public boolean valid() {
        return userId > 0 && productId > 0 && rating > 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
