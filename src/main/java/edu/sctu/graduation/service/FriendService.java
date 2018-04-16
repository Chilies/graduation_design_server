package edu.sctu.graduation.service;

import edu.sctu.graduation.common.ResponseData;

/**
 * Created by zhengsenwen on 2018/4/14.
 */
public interface FriendService {


    ResponseData getAllUserPhone();

    ResponseData getContactFriend(Integer userId, String phoneListJsonString);

    ResponseData fellowFriend(Integer userId, Integer friendId);


    ResponseData getAllFellowedFriend(Integer userId);

}
