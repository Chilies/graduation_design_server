package edu.sctu.graduation.service;

import edu.sctu.graduation.common.ResponseData;

/**
 * Created by zhengsenwen on 2018/4/10.
 */
public interface ReplyService {

    ResponseData toComment(Integer wishCardId, Integer fromUserId, Integer toUserId, String description);


    ResponseData getWishCardComment(Integer wishCardId);


}
