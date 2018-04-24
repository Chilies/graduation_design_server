package edu.sctu.graduation.service;

import edu.sctu.graduation.common.ResponseData;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhengsenwen on 2018/3/19.
 */
public interface WishService {

    ResponseData publishOneWishCard(
            String phoneNumber,
            String content,
            String price,
            String type,
            MultipartFile file);

    ResponseData getWishType();

    ResponseData getAllWish();

    ResponseData getAllFriendWish(Integer userId);

    ResponseData getOneWishByWishCardId(Integer wishCardId);

    ResponseData getUserWish(Integer userId);

}
