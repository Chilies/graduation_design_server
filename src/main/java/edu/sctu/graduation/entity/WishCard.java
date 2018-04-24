package edu.sctu.graduation.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhengsenwen on 2018/3/19.
 */
@Entity
@Table(name = "wish_card")
public class WishCard implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer wishCardId;
    private String createTime;
    private String description;
    private String price;
    private String type;
    private Integer userId;

    public Integer getWishCardId() {
        return wishCardId;
    }

    public void setWishCardId(Integer wishCardId) {
        this.wishCardId = wishCardId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
