package edu.sctu.graduation.controller;

import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhengsenwen on 2018/3/19.
 */

@RestController
@RequestMapping(value = "/wish")
public class WishController {

    @Autowired
    private WishService wishService;

    @RequestMapping(value = "/publish/one/wishCard", method = RequestMethod.POST)
    public ResponseData publishOneWishCard(String phoneNumber,
                                           String content,
                                           String price,
                                           String type,
                                           @RequestParam("file") MultipartFile file) {

        return wishService.publishOneWishCard(phoneNumber, content, price, type, file);
    }

    @RequestMapping(value = "/publish/type", method = RequestMethod.GET)
    public ResponseData getAllType() {
        return wishService.getWishType();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseData getAllWish() {
        return wishService.getAllWish();
    }

    @RequestMapping(value = "/friend", method = RequestMethod.GET)
    public ResponseData getFriendWish(Integer userId) {
        return wishService.getAllFriendWish(userId);
    }

    @RequestMapping(value = "/{wishCardId}", method = RequestMethod.GET)
    public ResponseData getOneWishCard(@PathVariable("wishCardId") Integer wishCardId) {
        return wishService.getOneWishByWishCardId(wishCardId);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseData getSomeOneWish(@PathVariable("userId") Integer userId) {
        return wishService.getUserWish(userId);
    }



}
