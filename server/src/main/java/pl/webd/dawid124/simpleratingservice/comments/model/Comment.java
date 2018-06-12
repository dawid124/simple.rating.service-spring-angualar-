package pl.webd.dawid124.simpleratingservice.comments.model;

import org.springframework.util.StringUtils;

import java.util.Date;

public class Comment {

    private long id;
    private String descriptions;
    private Date date;
    private String username;
    private long userId;
    private long productId;

    public Comment() {
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public boolean valid() {
        return userId > 0 && productId > 0 && StringUtils.hasText(descriptions);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
