package edu.sctu.graduation.service;

import edu.sctu.graduation.common.Constant;
import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.dao.AreaDao;
import edu.sctu.graduation.dao.AvatarDao;
import edu.sctu.graduation.dao.UserDao;
import edu.sctu.graduation.entity.Avatar;
import edu.sctu.graduation.entity.User;
import edu.sctu.graduation.json.UserInfo;
import edu.sctu.graduation.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AvatarDao avatarDao;
    @Autowired
    private AreaDao areaDao;

    /**
     * 注册
     *
     * @param phoneNumber
     * @param password
     * @param nickname
     * @param alipayAccount
     * @return
     */
    @Override
    public ResponseData register(String phoneNumber,
                                 String password,
                                 String nickname,
                                 String alipayAccount) {
        ResponseData responseData = new ResponseData();
        if (StringUtils.isBlank(phoneNumber)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)
                || StringUtils.isBlank(alipayAccount)) {
            responseData.setCode(Constant.REGISTER_ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }

        //检查用户是否已注册
        User loginUser = userDao.getUserByPhone(phoneNumber);
        if (loginUser != null) {
            responseData.setCode(Constant.USER_ALREADY_EXIST_CODE);
            responseData.setMsg(Constant.USER_ALREADY_EXIST_MSG);
            return responseData;
        }

        //将注册信息存入all_user表
        User user = new User();
        user.setTelephone(phoneNumber);
        user.setNickName(nickname);
        user.setPwd(password);
        user.setAlipayAccount(alipayAccount);

        try {
            userDao.saveAndFlush(user);
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setCode(Constant.REGISTER_ERROR_CODE);
            responseData.setMsg(Constant.REGISTER_ERROR_MSG);
            return responseData;
        }
    }

    /**
     * 登录
     *
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public ResponseData login(String phoneNumber,
                              String password) {
        ResponseData<Object> responseData = new ResponseData<Object>();
        if (StringUtils.isBlank(phoneNumber)
                || StringUtils.isBlank(password)) {
            responseData.setCode(Constant.LOGIN_ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }

        //检查用户是否已注册
        User loginUser = userDao.getUserByPhone(phoneNumber);
        if (loginUser == null) {
            responseData.setCode(Constant.NOT_FOUND_CODE);
            responseData.setMsg(Constant.USER_NOT_FOUND_MSG);
            return responseData;
        }

        //检查用户名和密码
        User user = userDao.login(phoneNumber, password);
        if (user == null) {
            responseData.setCode(Constant.LOGIN_ERROR_CODE);
            responseData.setMsg(Constant.LOGIN_ERROR_MSG);
        } else {
            List<Object> data = new ArrayList<>();
            data.add(user);
            responseData.setData(data);
        }

        return responseData;
    }

    @Override
    public ResponseData getArea() {
        ResponseData responseData = new ResponseData();
        List<String> areaList = new ArrayList<>();
        areaList = areaDao.getArea();
        List<Object> data = areaList.stream().collect(Collectors.toList());
        responseData.setData(data);
        return responseData;
    }

    @Override
    public ResponseData editUser(String nickname,
                                 String signature,
                                 String gender,
                                 String address,
                                 Integer userId) {
        ResponseData responseData = new ResponseData();
        if (StringUtils.isBlank(nickname)
                || StringUtils.isBlank(signature)
                || StringUtils.isBlank(gender)
                || StringUtils.isBlank(address)
                || (null == userId || userId == 0)) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }
        User user = userDao.getOne(userId);
        if (user == null) {
            responseData.setCode(Constant.NOT_FOUND_CODE);
            responseData.setMsg(Constant.USER_NOT_FOUND_MSG);
            return responseData;
        }
        String areaId = areaDao.getAreaId(address);
        if (StringUtils.isBlank(areaId)) {
            responseData.setCode(Constant.SYSTEM_ERROR_CODE);
            responseData.setMsg(Constant.ERROR_MSG);
            return responseData;
        }
        userDao.updateUser(nickname, signature, gender, Integer.valueOf(areaId), userId);
        List<Object[]> objects = userDao.getUserAllInfo(userId);
        if (objects.size() == 0) {
            responseData.setCode(Constant.NOT_FOUND_CODE);
            responseData.setMsg(Constant.USER_NOT_FOUND_MSG);
            return responseData;
        }
        responseData.setData(convertObject2UserInfoJson(objects));
        return responseData;
    }

    @Override
    public ResponseData getUserPassword(Integer userId) {
        ResponseData responseData = new ResponseData();
        if ((null == userId || userId == 0)) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setData(Constant.PARAM_ERROR);
            return responseData;
        }
        String password = userDao.getUserPassword(userId);
        if (password == null) {
            responseData.setCode(Constant.NOT_FOUND_CODE);
            responseData.setData(Constant.PASSWORD_ERROR_MSG);
            return responseData;
        }
        List<String> passwordList = new ArrayList<>();
        passwordList.add(userDao.getUserPassword(userId));
        List<Object> data = passwordList.stream().collect(Collectors.toList());
        responseData.setData(data);
        return responseData;
    }

    @Override
    public ResponseData updatePassword(String password, Integer userId) {
        ResponseData responseData = new ResponseData();
        if (StringUtils.isBlank(password)
                || (null == userId || userId == 0)) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setData(Constant.PARAM_ERROR);
        }
        User user = userDao.getOne(userId);
        if (user == null) {
            responseData.setCode(Constant.NOT_FOUND_CODE);
            responseData.setMsg(Constant.USER_NOT_FOUND_MSG);
            return responseData;
        }
        userDao.updatePassword(password, userId);
        User userData = userDao.getOne(userId);
        List<Object> data = new ArrayList<>();
        data.add(userData);
        responseData.setData(data);
        return responseData;
    }

    @Override
    public ResponseData getAllUserInfo(Integer userId) {
        ResponseData responseData = new ResponseData();
        if ((null == userId || userId == 0)) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setData(Constant.PARAM_ERROR);
            return responseData;
        }
        List<Object[]> objects = userDao.getUserAllInfo(userId);
        if (objects.size() == 0) {
            responseData.setCode(Constant.NOT_FOUND_CODE);
            responseData.setMsg(Constant.USER_NOT_FOUND_MSG);
            return responseData;
        }
        responseData.setData(convertObject2UserInfoJson(objects));
        return responseData;
    }

    private List<UserInfo> convertObject2UserInfoJson(List<Object[]> objects) {
        List<UserInfo> userInfoList = new ArrayList<>();
        for (Object[] o : objects) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId((Integer) (o[0]));
            userInfo.setNickName(String.valueOf(o[1]));
            userInfo.setPwd(String.valueOf(o[2]));
            userInfo.setSignature(String.valueOf(o[3]));
            userInfo.setTelephone(String.valueOf(o[4]));
            userInfo.setGender(String.valueOf(o[5]));
            userInfo.setReligionId((Integer) (o[6]));
            userInfo.setAlipayAccount(String.valueOf(o[7]));
            userInfo.setProvince(String.valueOf(o[8]));
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    @Override
    public ResponseData updateAvatar(Integer userId,
                                     MultipartFile avatarFile) {
        ResponseData responseData = new ResponseData();
        String fileName = "";
        String avatarSrc;

        if ((null == userId || userId == 0) || (avatarFile == null)) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.PARAM_ERROR);
            return responseData;
        }
        User user = userDao.getOne(userId);
        if (user != null) {
            fileName = user.getTelephone() + ".jpg";
            String filePath = Constant.FILE_PATH;
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                FileUtil.uploadFile(avatarFile.getBytes(), filePath, fileName);
            } catch (Exception e) {
                System.out.println("error: " + "file upload failed.");
                responseData.setCode(Constant.SYSTEM_ERROR_CODE);
                responseData.setMsg(Constant.ERROR_MSG);
                e.printStackTrace();
            }
        } else {
            System.out.println("file is null");
        }
        Avatar avatarRecord = avatarDao.getAvatarByUserId(userId);
        avatarSrc = Constant.IMAGE_ACCESS_PATH + fileName;
        if (avatarRecord != null) {
            avatarDao.updateAvatar(avatarSrc, userId);
        } else {
            Avatar avatar = new Avatar();
            avatar.setUserId(userId);
            avatar.setAvatarSrc(avatarSrc);
            avatarDao.saveAndFlush(avatar);
        }
        Avatar avatarData = avatarDao.getAvatarByUserId(userId);
        List<Object> data = new ArrayList<>();
        data.add(avatarData);
        responseData.setData(data);

        return responseData;
    }


}
