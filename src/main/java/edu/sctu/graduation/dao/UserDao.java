package edu.sctu.graduation.dao;

import edu.sctu.graduation.entity.User;
import edu.sctu.graduation.json.ContactFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {


//    select u.*, (select a.province from area a where a.id = u.religion_id) as province
//    from all_user u where u.id = 12
    @Query("SELECT u.id,u.nickName,u.pwd,u.signature,u.telephone,u.gender," +
            " u.religionId,u.alipayAccount," +
            "(select a.province from Area a where a.id = u.religionId) as province " +
            "FROM User u where u.id = ?1")
    public List<Object[]> getUserAllInfo(Integer id);

    @Query("from User where telephone=?1")
    public User getUserByPhone(String telephone);

    @Query("from User where telephone=?1 and pwd=?2")
    public User login(String phone, String password);

    //    UPDATE all_user u SET u.nick_name="bbb" ,
    //    u.signature="有志者事竟成" ,u.gender="女" ,
    //    u.religion_id="2"  WHERE u.id= 17
    @Modifying
    @Query("UPDATE User u SET u.nickName=?1 , u.signature=?2 ,u.gender=?3 ,u.religionId=?4" +
            " WHERE u.id=?5")
    public void updateUser(String nickname, String signature, String gender, Integer religionId, Integer userId);

    //    select all_user.pwd from all_user where all_user.id = 19
    @Query("SELECT u.pwd from User u where u.id=?1")
    public String getUserPassword(Integer userId);

    //    update all_user set pwd = "147qaz" where id=19
    @Modifying
    @Query("UPDATE User u SET u.pwd=?1 WHERE u.id=?2")
    public void updatePassword(String password, Integer userId);

    //    select f.friend_id from friend f where f.user_id = 19
    @Query("SELECT f.friendId from Friend f where f.userId=?1")
    public List<Integer> getUserFriend(Integer userId);

}
