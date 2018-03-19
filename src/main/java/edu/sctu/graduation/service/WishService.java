package edu.sctu.graduation.service;

import edu.sctu.graduation.common.ResponseData;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhengsenwen on 2018/3/19.
 */
public interface WishService {

    ResponseData publishOneWishCard(String phoneNumber,String Content, MultipartFile file, String price, String type);
}
