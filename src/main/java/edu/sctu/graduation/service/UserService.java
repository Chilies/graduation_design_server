package edu.sctu.graduation.service;

import edu.sctu.graduation.common.ResponseData;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    ResponseData login(String phone, String pwd);

    ResponseData register(String phone, String pwd, String nickname, String alipayAccount);

    ResponseData editUser(String phone, String nickname, String selfIntroduction, MultipartFile file);

    ResponseData updateAvatar(Integer userId, MultipartFile avatarFile);
}
