package edu.sctu.graduation.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengsenwen on 2018/3/23.
 */


public class WishCardJson implements Serializable {
    private static final Long serialVersionUID = 1L;

    private List<WishCardContentJson> wishCardContentJsonList;



    public List<WishCardContentJson> getWishCardContentJsonList() {
        return wishCardContentJsonList;
    }

    public void setWishCardContentJsonList(List<WishCardContentJson> wishCardContentJsonList) {
        this.wishCardContentJsonList = wishCardContentJsonList;
    }
}
