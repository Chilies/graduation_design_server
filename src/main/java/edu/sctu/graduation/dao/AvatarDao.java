package edu.sctu.graduation.dao;

import edu.sctu.graduation.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zhengsenwen on 2018/3/22.
 */
public interface AvatarDao extends JpaRepository<Avatar, Integer> {

    @Query("from Avatar where userId=?1")
    public Avatar getAvatarByUserId(int userId);

    @Modifying
    @Query("UPDATE Avatar a SET a.avatarSrc=?1  WHERE a.userId=?2")
    public void updateAvatar(String avatarSrc, int userId);


}
