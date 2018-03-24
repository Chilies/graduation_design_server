package edu.sctu.graduation.common;

public class Constant {

    //默认 code = 200  msg = "success" data = null

    //注册失败
    public static final Integer REGISTER_ERROR_CODE = 0;
    //登陆失败
    public static final Integer LOGIN_ERROR_CODE = 1;
    //失败
    public static final Integer ERROR_CODE = 2;
    //系统错误代码
    public static final Integer SYSTEM_ERROR_CODE = 500;
    //成功
    public static final Integer SUCCESS_CODE = 200;
    //token失效
    public static final Integer TOKEN_INVALID = 401;
    //404错误
    public static final Integer NOT_FOUND_CODE = 404;
    //文件上传根路径
    public static final String FILE_PATH = "/home/www/image/";
    //图片访问路径
    public static final String IMAGE_ACCESS_PATH = "http://swzheng.cn/image/";
    // 用户已存在
    public static final Integer USER_ALREADY_EXIST_CODE = 100;

    //操作失败
    public static final String ERROR_MSG = "操作失败";


    public static final String REGISTER_ERROR_MSG = "注册失败";
    public static final String LOGIN_ERROR_MSG = "登陆失败";
    public static final String PARAM_ERROR = "参数错误";
    public static final String USER_NOT_FOUND_MSG = "用户不存在";
    public static final String USER_ALREADY_EXIST_MSG = "用户已存在";

}
