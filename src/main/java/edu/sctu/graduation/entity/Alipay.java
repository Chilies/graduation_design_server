package edu.sctu.graduation.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhengsenwen on 2018/4/3.
 */

@Entity
@Table(name = "alipay_transfer")
public class Alipay implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int alipayId;
    private String alipayReceiveCode;
    private int userId;

    public int getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(int alipayId) {
        this.alipayId = alipayId;
    }

    public String getAlipayReceiveCode() {
        return alipayReceiveCode;
    }

    public void setAlipayReceiveCode(String alipayReceiveCode) {
        this.alipayReceiveCode = alipayReceiveCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
