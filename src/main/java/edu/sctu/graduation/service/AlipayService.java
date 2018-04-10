package edu.sctu.graduation.service;

import edu.sctu.graduation.common.ResponseData;

/**
 * Created by zhengsenwen on 2018/4/3.
 */
public interface AlipayService {


    ResponseData getAlipayByUserId(Integer userId);

    ResponseData uploadAlipayReceiveCode(String receiveCode, Integer userId);
}
