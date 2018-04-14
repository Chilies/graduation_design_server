package edu.sctu.graduation.controller;

import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengsenwen on 2018/4/14.
 */

@RestController
@RequestMapping(value = "/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @RequestMapping(value = "/all/phone", method = RequestMethod.GET)
    public ResponseData getAllUserPhone() {
        return friendService.getAllUserPhone();
    }


    @RequestMapping(value = "/contact/friend", method = RequestMethod.POST)
    public ResponseData getContactFriend(String phoneListJsonString) {
        System.out.println("friend" + phoneListJsonString);
        return friendService.getContactFriend(phoneListJsonString);
    }


    @RequestMapping(value = "/fellow", method = RequestMethod.POST)
    public ResponseData fellowFriend(Integer userId, Integer friendId) {
        System.out.println("friend" + userId + friendId);
        return friendService.fellowFriend(userId, friendId);
    }

}
