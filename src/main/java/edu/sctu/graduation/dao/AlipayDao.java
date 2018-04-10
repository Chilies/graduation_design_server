package edu.sctu.graduation.dao;

import edu.sctu.graduation.entity.Alipay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by zhengsenwen on 2018/4/3.
 */

public interface AlipayDao extends JpaRepository<Alipay, Integer> {

    @Query("from Alipay where userId=?1")
    public Alipay getAvatarByUserId(int userId);

}
