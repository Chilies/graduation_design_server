package edu.sctu.graduation.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import edu.sctu.graduation.common.Constant;
import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.dao.FriendDao;
import edu.sctu.graduation.entity.Friend;
import edu.sctu.graduation.json.ContactFriend;
import edu.sctu.graduation.json.AllFriend;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhengsenwen on 2018/4/14.
 */

@Transactional
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDao friendDao;


    @Override
    public ResponseData getAllUserPhone() {
        ResponseData responseData = new ResponseData();
        List<String> phoneList = new ArrayList<>();
        phoneList = friendDao.getAllUserPhone();
        List<Object> data = phoneList.stream().collect(Collectors.toList());
        responseData.setData(data);
        return responseData;
    }

    @Override
    public ResponseData getContactFriend(Integer userId, String phoneListJsonString) {
        ResponseData responseData = new ResponseData();
        if (StringUtils.isBlank(phoneListJsonString)) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }

        List<String> stringList = JSON.parseObject(phoneListJsonString,
                new TypeReference<List<String>>() {
                }.getType());

        System.out.println("friend222" + stringList.size());

        List<ContactFriend> contactFriendList = new ArrayList<>();
        for (String phone : stringList) {
            List<Object[]> objects = friendDao.getContactFriend(userId, phone);
            contactFriendList.addAll(convertObjects2Json(objects));
        }
        responseData.setData(contactFriendList);
        return responseData;

    }

    private List<ContactFriend> convertObjects2Json(List<Object[]> objectList) {
        List<ContactFriend> data = new ArrayList<>();
        for (Object[] o : objectList) {
            ContactFriend contactFriend = new ContactFriend();
            contactFriend.setId((Integer) o[0]);
            contactFriend.setNickName(String.valueOf(o[1]));
            contactFriend.setPhoneNumber(String.valueOf(o[2]));
            contactFriend.setAvatarSrc(String.valueOf(o[3]));
            contactFriend.setFellowStatus((Integer)o[4]);
            data.add(contactFriend);
        }
        return data;
    }


    @Override
    public ResponseData fellowFriend(Integer userId, Integer friendId) {
        ResponseData responseData = new ResponseData();
        if ((userId == null) || (friendId == null)) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }

        Integer fellowStatus;
        Friend friend = friendDao.checkFellowStatus(friendId, userId);
        if (friend == null) {
            fellowStatus = Constant.NOT_FELLOW_CODE;
        } else {
            fellowStatus = Constant.BOTH_FELLOW_CODE;
            //当检测为互相关注时，将先关注者的记录中关注状态修改为1
            friendDao.updateFellowStatus(Constant.BOTH_FELLOW_CODE, friend.getId());
        }
        System.out.println(userId + fellowStatus + friendId);
        Friend friendSave = new Friend();
        friendSave.setUserId(userId);
        friendSave.setFriendId(friendId);
        friendSave.setFellowStatus(fellowStatus);

        friendDao.saveAndFlush(friendSave);

        Friend friendData = friendDao.getOne(friendSave.getId());
        List<Object> data = new ArrayList<>();
        data.add(friendData);
        responseData.setData(data);

        return responseData;
    }


    @Override
    public ResponseData getAllFellowedFriend(Integer userId) {
        ResponseData responseData = new ResponseData();
        if (userId == null) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }

        List<Object[]> objects = friendDao.getAllFellowedFriend(userId);
        responseData.setData(convertObjects2MyALLFriendList(objects));

        return responseData;
    }

    private List<AllFriend> convertObjects2MyALLFriendList(List<Object[]> objectList) {
        List<AllFriend> data = new ArrayList<>();
        for (Object[] o : objectList) {
            AllFriend allFriend = new AllFriend();
            allFriend.setId((Integer) o[0]);
            allFriend.setNickName(String.valueOf(o[1]));
            allFriend.setFellowStatus((Integer) o[2]);
            allFriend.setAvatarSrc(String.valueOf(o[3]));
            data.add(allFriend);
        }
        return data;
    }

}
