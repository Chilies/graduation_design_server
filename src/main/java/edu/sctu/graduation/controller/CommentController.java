package edu.sctu.graduation.controller;

import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.dao.ReplyDao;
import edu.sctu.graduation.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengsenwen on 2018/4/10.
 */

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private ReplyDao replyDao;
    @Autowired
    private ReplyService replyService;

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public ResponseData toComment(Integer wishCardId,
                                  Integer fromUserId,
                                  Integer toUserId,
                                  String description) {

        return replyService.toComment(wishCardId, fromUserId, toUserId, description);
    }

    @RequestMapping(value = "/wish/card/details",method = RequestMethod.GET)
    public ResponseData getWishCardComment(Integer wishCardId) {
        return replyService.getWishCardComment(wishCardId);
    }

}
