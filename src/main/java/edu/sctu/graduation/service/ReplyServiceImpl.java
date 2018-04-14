package edu.sctu.graduation.service;

import edu.sctu.graduation.common.Constant;
import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.dao.ReplyDao;
import edu.sctu.graduation.entity.Reply;
import edu.sctu.graduation.json.CommentJson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhengsenwen on 2018/4/10.
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Override
    public ResponseData toComment(Integer wishCardId,
                                  Integer fromUserId,
                                  Integer toUserId,
                                  String description) {
        ResponseData responseData = new ResponseData();
        if ((null == wishCardId) && (wishCardId == 0)
                || (null == fromUserId) && (fromUserId == 0)
                || (StringUtils.isBlank(description))) {

            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }

        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        Reply reply = new Reply();
        reply.setPubdate(time);
        reply.setWishCardId(wishCardId);
        reply.setFromUserId(fromUserId);
        System.out.println("toUserId" + toUserId);
        if (null != toUserId) {
            reply.setToUserId(toUserId);
        }
        reply.setDescription(description);
        replyDao.saveAndFlush(reply);

        Reply replyData = new Reply();
        replyData = replyDao.getOne(reply.getReplyId());
        List<Object> data = new ArrayList<>();
        data.add(replyData);
        responseData.setData(data);

        return responseData;
    }

    @Override
    public ResponseData getWishCardComment(Integer wishCardId) {
        ResponseData responseData = new ResponseData();

        if (null == wishCardId || wishCardId == 0) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }
        List<Object[]> objects = replyDao.getWishCardComment(wishCardId);
        System.out.println((objects.get(0))[2] + "" + (objects.get(0))[5]);
        responseData.setData(convertObject2CommentJson(objects));

        return responseData;
    }

    private List<CommentJson> convertObject2CommentJson(List<Object[]> objects) {
        List<CommentJson> commentJsonList = new ArrayList<>();
        for (Object[] o : objects) {
            CommentJson commentJson = new CommentJson();
            commentJson.setReplyId((Integer) (o[0]));
            commentJson.setPubdate(String.valueOf(o[1]));
            commentJson.setDescription(String.valueOf(o[2]));
            commentJson.setWishCardId((Integer) (o[3]));
            commentJson.setFromUserId((Integer) (o[4]));
            commentJson.setToUserId((Integer) o[5]);
            commentJson.setFromUserNickName(String.valueOf(o[6]));
            commentJson.setToUserNickName(String.valueOf(o[7]));
            commentJsonList.add(commentJson);
        }

        return commentJsonList;
    }


}
