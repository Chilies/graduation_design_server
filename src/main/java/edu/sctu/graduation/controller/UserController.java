package edu.sctu.graduation.controller;

import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.service.UserService;
import edu.sctu.graduation.utils.DesUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseData register(String phoneNumber,
                                 String password,
                                 String nickname,
                                 String alipayAccount) {

        return userService.register(
                DesUtils.decrypt(phoneNumber),
                DesUtils.decrypt(password),
                nickname,
                alipayAccount);
    }

    /**
     * 登录
     *
     * @param phoneNumber
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(String phoneNumber,
                              String password) {

        return userService.login(DesUtils.decrypt(phoneNumber),
                DesUtils.decrypt(password));
    }

    /**
     * 更新用户头像
     *
     * @param userId
     * @param file
     * @return
     */
    @RequestMapping(value = "/update/avatar", method = RequestMethod.POST)
    public ResponseData update(Integer userId,
                               @RequestParam("file") MultipartFile file) {

        return userService.updateAvatar(userId, file);
    }

    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public ResponseData getUserArea() {
        return userService.getArea();
    }


    @RequestMapping(value = "/update/info", method = RequestMethod.POST)
    public ResponseData update(String nickname,
                               String signature,
                               String gender,
                               String address,
                               Integer userId) {
        return userService.editUser(nickname, signature, gender, address, userId);
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ResponseData update(Integer userId) {
        System.out.println(userId);
        return userService.getUserPassword(userId);
    }

    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public ResponseData update(String password, Integer userId) {
        return userService.updatePassword(password, userId);
    }

    @RequestMapping(value = "/all/info", method = RequestMethod.POST)
    public ResponseData getUserAllInfo(Integer userId) {
        return userService.getAllUserInfo(userId);
    }


}
