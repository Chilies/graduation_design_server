package edu.sctu.graduation.service;

import edu.sctu.graduation.common.Constant;
import edu.sctu.graduation.common.ResponseData;
import edu.sctu.graduation.dao.AlipayDao;
import edu.sctu.graduation.entity.Alipay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengsenwen on 2018/4/3.
 */

@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    private AlipayDao alipayDao;

    @Override
    public ResponseData getAlipayByUserId(Integer userId) {
        ResponseData responseData = new ResponseData();

        Alipay alipay = alipayDao.getAvatarByUserId(userId);
        if (alipay != null) {
            responseData.setCode(Constant.ALIPAY_ERROR_CODE);
            responseData.setMsg(Constant.ALIPAY_EXIST_MSG);
            return responseData;
        }

        return responseData;
    }

    @Override
    public ResponseData uploadAlipayReceiveCode(String receiveCode, Integer userId) {
        ResponseData responseData = new ResponseData();

        Alipay alipay = new Alipay();
        alipay.setAlipayReceiveCode(receiveCode);
        alipay.setUserId(userId);

        try {
            alipayDao.saveAndFlush(alipay);

            Alipay alipayData = alipayDao.getOne(alipay.getAlipayId());
            List<Object> data = new ArrayList<>();
            data.add(alipayData);
            responseData.setData(data);

        } catch (Exception e) {
            responseData.setCode(Constant.ERROR_CODE);
            responseData.setMsg(Constant.ERROR_MSG);
            e.printStackTrace();
        }

        return responseData;
    }


}
