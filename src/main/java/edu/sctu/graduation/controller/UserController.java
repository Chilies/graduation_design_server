package edu.sctu.graduation.controller;

import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.service.UserService;
import edu.sctu.graduation.utils.DesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
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
     * 登陆
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public ResponseData login(HttpServletRequest req) {
        String phone = req.getParameter("phone");
        String pwd = req.getParameter("pwd");
        return userService.login(DesUtils.decrypt(phone), DesUtils.decrypt(pwd));

    }

    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseData login(String phoneNumber,
                              String password){

        return userService.login(
                DesUtils.decrypt(phoneNumber),
                DesUtils.decrypt(password));
	}




    /**
     * 修改用户信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData update(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        String phone = req.getParameter("phone");
        String nickname = req.getParameter("nickname");
        String selfIntroduction = req.getParameter("selfIntroduction");

        return userService.editUser(phone, nickname, selfIntroduction, file);
    }
}
