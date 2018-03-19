package edu.sctu.graduation.service;

import edu.sctu.graduation.common.Constant;
import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.dao.UserDao;
import edu.sctu.graduation.dao.WishCardDao;
import edu.sctu.graduation.dao.WishCardImgDao;
import edu.sctu.graduation.entity.User;
import edu.sctu.graduation.entity.WishCard;
import edu.sctu.graduation.entity.WishCardImg;
import edu.sctu.graduation.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by zhengsenwen on 2018/3/19.
 */
@Service
public class WishServiceImpl implements WishService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private WishCardDao wishCardDao;
    @Autowired
    private WishCardImgDao wishCardImgDao;

    @Override

    public ResponseData publishOneWishCard(String phoneNumber,
                                           String content,
                                           MultipartFile file,
                                           String price,
                                           String type) {
        ResponseData responseData = new ResponseData();
        String createTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = "";

        try {
            if (StringUtils.isBlank(content)
                    && StringUtils.isBlank(price)
                    && StringUtils.isBlank(type)
                    && file == null) {
                responseData.setCode(Constant.ERROR_CODE);
                responseData.setMsg(Constant.PARAM_MSG);
                return responseData;
            }
            User user = userDao.getUserByPhone(phoneNumber);
            if (user == null) {
                responseData.setCode(Constant.NOT_FOUND_CODE);
                responseData.setMsg(Constant.USER_NOT_FOUND_MSG);
                return responseData;
            }

            //time userId price content type img
            WishCard wishCard = new WishCard();
            WishCardImg wishCardImg = new WishCardImg();

            wishCard.setCreateTime(createTime);
            wishCard.setUserId(user.getId());
            if (price != null && !"".equals(price)) {
                wishCard.setPrice(price);
            }
            if (content != null && !"".equals(content)) {
                wishCard.setDescription(content);
            }
            if (type != null && !"".equals(type)) {
                wishCard.setType(type);
            }
            if (file != null) {
                String contentType = file.getContentType();
                fileName = phoneNumber + createTime
                        + "." + FileUtil.getExtensionName(file.getOriginalFilename());

                String filePath = Constant.FILE_PATH;
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            }else {
                System.out.println("file is null");
            }

            wishCard.setDescription(content);
            wishCardDao.saveAndFlush(wishCard);

            WishCard wishCardData = wishCardDao.getOne(wishCard.getId());
            List<Object> data = new ArrayList<>();
            data.add(wishCardData);
            responseData.setData(data);

            wishCardImg.setWishCardImgSrc(fileName);
            wishCardImg.setWishCardId(wishCard.getId());
            wishCardImgDao.saveAndFlush(wishCardImg);

        } catch (Exception e) {
            responseData.setCode(Constant.SYSTEM_ERROR_CODE);
            responseData.setMsg(Constant.ERROR_MSG);
            e.printStackTrace();
        }

        return responseData;
    }
}
