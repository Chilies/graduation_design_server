package edu.sctu.graduation.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhengsenwen on 2018/3/19.
 */

@Entity
@Table(name = "wish_card_img")
public class WishCardImg implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String wishCardImgSrc;
    private int wishCardId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWishCardImgSrc() {
        return wishCardImgSrc;
    }

    public void setWishCardImgSrc(String wishCardImgSrc) {
        this.wishCardImgSrc = wishCardImgSrc;
    }

    public int getWishCardId() {
        return wishCardId;
    }

    public void setWishCardId(int wishCardId) {
        this.wishCardId = wishCardId;
    }
}