package edu.sctu.graduation.json;

import java.io.Serializable;

/**
 * Created by zhengsenwen on 2018/3/23.
 */
public class WishCardContentJson implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String avatarSrc;
    private String nickName;
    private Integer wishCardId;
    private String createTime;
    private String price;
    private String type;
    private String description;
    private String wishCardImgSrc;
    private String alipayReceiveCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWishCardImgSrc() {
        return wishCardImgSrc;
    }

    public void setWishCardImgSrc(String wishCardImgSrc) {
        this.wishCardImgSrc = wishCardImgSrc;
    }

    public String getAlipayReceiveCode() {
        return alipayReceiveCode;
    }

    public void setAlipayReceiveCode(String alipayReceiveCode) {
        this.alipayReceiveCode = alipayReceiveCode;
    }
}
