package edu.sctu.graduation.dao;

import edu.sctu.graduation.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhengsenwen on 2018/4/14.
 */
public interface FriendDao extends JpaRepository<Friend, Integer> {


    //    select telephone from all_user
    @Query("SELECT telephone FROM User")
    public List<String> getAllUserPhone();


    //    select u.id,u.nick_name,u.telephone,
//            (select a.avatar_src from avatar a where a.user_id = u.id) as avatarSrc
//    from all_user u
//    where u.telephone = "18328023102"
    @Query("SELECT u.id,u.nickName,u.telephone," +
            "(select a.avatarSrc from Avatar a where a.userId = u.id) as avatarSrc" +
            " FROM User u where u.telephone = ?1")
    public List<Object[]> getContactFriend(String phoneNumber);


    //    select * from friend where  friend_id = 21 and user_id  = 20
    @Query("from Friend where userId = ?1 and friendId = ?2")
    public Friend checkFellowStatus(Integer friendId, Integer userId);

    //    update friend set fellow_status = 1 where id = 20
    @Modifying
    @Query("UPDATE Friend f set f.fellowStatus =?1 where f.id = ?2")
    public void updateFellowStatus(Integer fellowStatus, Integer id);


}
