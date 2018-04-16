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
//            (select a.avatar_src from avatar a where a.user_id = u.id) as avatarSrc,
//    (select f.fellow_status from friend f where user_id = 19 and friend_id = u.id) as fellowStatus
//    from all_user u
//    where u.telephone = "18328023100"
    @Query("SELECT u.id,u.nickName,u.telephone," +
            "(select a.avatarSrc from Avatar a where a.userId = u.id) as avatarSrc," +
            "(select f.fellowStatus from Friend f where userId = ?1 and friendId = u.id) as fellowStatus" +
            " FROM User u where u.telephone = ?2")
    public List<Object[]> getContactFriend(Integer userId , String phoneNumber);


    //    select * from friend where  friend_id = 21 and user_id  = 20
    @Query("from Friend where userId = ?1 and friendId = ?2")
    public Friend checkFellowStatus(Integer friendId, Integer userId);

    //    update friend set fellow_status = 1 where id = 20
    @Modifying
    @Query("UPDATE Friend f set f.fellowStatus =?1 where f.id = ?2")
    public void updateFellowStatus(Integer fellowStatus, Integer id);


//    select u.id,u.nick_name, f.fellow_status,
//            (select a.avatar_src from avatar a where a.user_id = u.id )
//    from friend f, all_user u
//    where f.friend_id = u.id
//    and f.user_id = 19
    @Query("SELECT u.id,u.nickName,f.fellowStatus," +
            "(select a.avatarSrc from Avatar a where a.userId = u.id) as avatarSrc" +
            " FROM User u,Friend f where u.id = f.friendId" +
            " and f.userId = ?1")
    public List<Object[]> getAllFellowedFriend(Integer userId);

}
