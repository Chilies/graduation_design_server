package edu.sctu.graduation.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by zhengsenwen on 2018/3/19.
 */
@Entity
@Table(name = "wish_card")
public class WishCard implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wishCardId;
    private String createTime;
    private String description;
    private String price;
    private String type;
    private int giverId;
    private int userId;
    private int avatarId;
    private int replyId;
    private Timestamp deadline;

    public int getWishCardId() {
        return wishCardId;
    }

    public void setWishCardId(int wishCardId) {
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

    public int getGiverId() {
        return giverId;
    }

    public void setGiverId(int giverId) {
        this.giverId = giverId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }
}
