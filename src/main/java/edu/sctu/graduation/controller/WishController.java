package edu.sctu.graduation.controller;

import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

}
