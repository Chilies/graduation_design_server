package edu.sctu.graduation.common;

public class Constant {

    //默认 code = 200  msg = "success" data = null

    //注册失败
    public static final Integer REGISTER_ERROR_CODE = 0;
    //登陆失败
    public static final Integer LOGIN_ERROR_CODE = 1;
    //失败
    public static final Integer ERROR_CODE = 2;
    //用户上传收钱码失败
    public static final Integer ALIPAY_ERROR_CODE = 3;
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


    //用户已上传收钱码
    public static final String ALIPAY_EXIST_MSG = "用户已上传过收钱码";
    //操作失败
    public static final String ERROR_MSG = "操作失败";


    public static final String REGISTER_ERROR_MSG = "注册失败";
    public static final String LOGIN_ERROR_MSG = "登陆失败";
    public static final String PARAM_ERROR = "参数错误";
    public static final String USER_NOT_FOUND_MSG = "用户不存在";
    public static final String USER_ALREADY_EXIST_MSG = "用户已存在";
    public static final String PASSWORD_ERROR_MSG = "密码错误";


    public static final String ALREADY_FELLOWED_MSG = "已关注该用户";

    //单方关注
    public static final Integer NOT_FELLOW_CODE = 0;
    //互相关注
    public static final Integer BOTH_FELLOW_CODE = 1;

}
