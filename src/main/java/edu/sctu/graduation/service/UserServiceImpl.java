package edu.sctu.graduation.service;

import com.google.common.collect.Lists;
import edu.sctu.graduation.common.Constant;
import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.dao.UserDao;
import edu.sctu.graduation.entity.User;
import edu.sctu.graduation.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

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
            responseData.setMsg(Constant.PARAM_MSG);
            return responseData;
        }

        //检查用户是否已注册
        User loginUser = userDao.getUserByPhone(phoneNumber);
        if (loginUser != null) {
            responseData.setCode(Constant.USER_ALREADY_EXIST_CODE);
            responseData.setMsg(Constant.USER_ALREADY_EXIST_MSG);
            return responseData;
        }

        User user = new User();
        user.setTelephone(phoneNumber);
        user.setNickName(nickname);
        user.setPassword(password);
        user.setAlipayAccount(alipayAccount);

        try {
            userDao.saveAndFlush(user);
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setMsg(Constant.REGISTER_ERROR_MSG);
            return responseData;
        }
    }

    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public ResponseData login(String phoneNumber,
                              String password) {
        ResponseData responseData = new ResponseData();
        if (StringUtils.isBlank(phoneNumber)
                || StringUtils.isBlank(password)) {
            responseData.setCode(Constant.LOGIN_ERROR_CODE);
            responseData.setMsg(Constant.PARAM_MSG);
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
    public ResponseData editUser(String phone, String nickname, String selfIntroduction, MultipartFile file) {

        ResponseData response = new ResponseData();
        try {
            if (StringUtils.isBlank(phone) || StringUtils.isBlank(nickname) || StringUtils.isBlank(selfIntroduction)
                    || file == null) {
                response.setCode(Constant.LOGIN_ERROR_CODE);
                response.setMsg(Constant.PARAM_MSG);
                return response;
            }
            User user = userDao.getUserByPhone(phone);
            if (user == null) {
                response.setCode(Constant.NOT_FOUND_CODE);
                response.setMsg(Constant.USER_NOT_FOUND_MSG);
                return response;
            }
            // 调用文件处理类FileUtil，处理文件，将文件写入指定位置
            String contentType = file.getContentType(); // 图片文件类型
            String fileName = phone + "." + FileUtil.getExtensionName(file.getOriginalFilename());  //图片名字
            // 文件存放路径
            String filePath = Constant.FILE_PATH + "/img";
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            user.setNickName(nickname);
            user.setSignature(selfIntroduction);
            user.setAvatarId(fileName);
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            userDao.saveAndFlush(user);
            User users = userDao.getUserByPhone(phone);
            List<Object> data = Lists.newArrayList();
            data.add(users);
            response.setData(data);

        } catch (Exception e) {
            response.setCode(Constant.SYSTEM_ERROR_CODE);
            response.setMsg(Constant.ERROR_MSG);
            e.printStackTrace();

        }
        return response;
    }

}
