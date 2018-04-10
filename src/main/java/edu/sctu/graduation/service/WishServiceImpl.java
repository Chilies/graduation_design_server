package edu.sctu.graduation.service;

import edu.sctu.graduation.common.Constant;
import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.dao.TypeDao;
import edu.sctu.graduation.dao.UserDao;
import edu.sctu.graduation.dao.WishCardDao;
import edu.sctu.graduation.dao.WishCardImgDao;
import edu.sctu.graduation.entity.User;
import edu.sctu.graduation.entity.WishCard;
import edu.sctu.graduation.entity.WishCardImg;
import edu.sctu.graduation.json.WishCardContentJson;
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
import java.util.stream.Collectors;


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
    @Autowired
    private TypeDao typeDao;

    @Override
    public ResponseData getWishType() {
        ResponseData responseData = new ResponseData();

        List<String> typeList = new ArrayList<>();
        typeList = typeDao.getAllType();
        List<Object> data = typeList.stream().collect(Collectors.toList());
        responseData.setData(data);
        return responseData;
    }

    @Override
    public ResponseData getAllWish() {
        ResponseData responseData = new ResponseData();
        List<Object[]> objectList = wishCardDao.getAllWish();
        responseData.setData(convertObjects2Json(objectList));
        return responseData;
    }

    private List<WishCardContentJson> convertObjects2Json(List<Object[]> objectList) {
        List<WishCardContentJson> data = new ArrayList<>();
        for (Object[] o : objectList) {
            WishCardContentJson wishCardContentJson
                    = new WishCardContentJson();
            wishCardContentJson.setId((Integer) o[0]);
            wishCardContentJson.setNickName(String.valueOf(o[1]));
            wishCardContentJson.setAvatarSrc(String.valueOf(o[2]));
            wishCardContentJson.setWishCardId((Integer) o[3]);
            wishCardContentJson.setCreateTime(String.valueOf(o[4]));
            wishCardContentJson.setDescription(String.valueOf(o[5]));
            wishCardContentJson.setPrice((o[6]).toString());
            wishCardContentJson.setType(String.valueOf(o[7]));
            wishCardContentJson.setWishCardImgSrc(String.valueOf(o[8]));
            data.add(wishCardContentJson);
        }
        return data;
    }




    @Override
    public ResponseData getOneWishByWishCardId(Integer wishCardId) {
        ResponseData responseData = new ResponseData();
        if (null == wishCardId || wishCardId == 0) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }
        List<Object[]> wishCardContentJsonList
                = wishCardDao.getOneWishCard(wishCardId);
        responseData.setData(convertObjects2Json(wishCardContentJsonList));
        return responseData;
    }

    @Override
    public ResponseData publishOneWishCard(String phoneNumber,
                                           String content,
                                           String price,
                                           String type,
                                           MultipartFile file) {

        ResponseData responseData = new ResponseData();
        String createTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = "";

        if (StringUtils.isBlank(content)
                || StringUtils.isBlank(price)
                || StringUtils.isBlank(type)
                || file == null) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
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
        wishCard.setPrice(price);
        wishCard.setDescription(content);
        wishCard.setType(type);

        if (file != null) {
            fileName = phoneNumber + createTime + ".jpg";
            String filePath = Constant.FILE_PATH;
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                FileUtil.uploadFile(file.getBytes(),
                        filePath, fileName);
            } catch (Exception e) {
                System.out.println("error: "
                        + "file upload failed.");
                responseData.setCode(Constant.SYSTEM_ERROR_CODE);
                responseData.setMsg(Constant.ERROR_MSG);
                e.printStackTrace();
            }
        } else {
            System.out.println("file is null");
        }
        //将心愿单的内容添加到wish_card表中
        wishCard.setDescription(content);
        wishCardDao.saveAndFlush(wishCard);
        WishCard wishCardData = wishCardDao
                .getOne(wishCard.getWishCardId());
        List<Object> data = new ArrayList<>();
        data.add(wishCardData);
        responseData.setData(data);

        //将心愿单的图片访问路径存到wish_card_img表中
        wishCardImg.setWishCardImgSrc(Constant
                .IMAGE_ACCESS_PATH + fileName);
        wishCardImg.setWishCardId(wishCard.getWishCardId());
        wishCardImgDao.saveAndFlush(wishCardImg);

        return responseData;
    }


}
