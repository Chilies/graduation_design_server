package edu.sctu.graduation.service;

import edu.sctu.graduation.common.ResponseData;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    ResponseData login(String phone, String pwd);

    ResponseData register(String phone, String pwd, String nickname, String alipayAccount);

    ResponseData updateAvatar(Integer userId, MultipartFile avatarFile);

    ResponseData getArea();

    ResponseData editUser(String nickname, String signature,String gender,String address,Integer userId);

    ResponseData getUserPassword(Integer userId);

    ResponseData updatePassword(String password, Integer userId);

    ResponseData getAllUserInfo(Integer userId);

}
