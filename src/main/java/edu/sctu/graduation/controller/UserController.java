package edu.sctu.graduation.controller;

import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.service.UserService;
import edu.sctu.graduation.utils.DesUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseData register(  String phoneNumber,
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
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(String phoneNumber,
                              String password) {

        return userService.login(DesUtils.decrypt(phoneNumber),
                DesUtils.decrypt(password));
    }


    /**
     * 修改用户信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
//    @ResponseBody
    public ResponseData update(@RequestParam("file") MultipartFile file,
                               HttpServletRequest req) {
        String phone = req.getParameter("phone");
        String nickname = req.getParameter("nickname");
        String selfIntroduction = req.getParameter("selfIntroduction");

        return userService.editUser(phone, nickname, selfIntroduction, file);
    }
}
