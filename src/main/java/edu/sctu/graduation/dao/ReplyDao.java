package edu.sctu.graduation.dao;

import edu.sctu.graduation.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhengsenwen on 2018/4/10.
 */
public interface ReplyDao extends JpaRepository<Reply, Integer> {



//    select r.*,
//    (select u0.nick_name from all_user u0 where u0.id = r.from_user_id) nickname0,
//    (select u1.nick_name from all_user u1 where u1.id = r.to_user_id) nickname1
//    from reply r
//    where r.wish_card_id = 28
    @Query("select r.replyId,r.pubdate,r.description,r.wishCardId,r.fromUserId,r.toUserId," +
            " (select u0.nickName from User u0 where u0.id = r.fromUserId ) as fromUserNickName," +
            " (select u1.nickName from User u1 where u1.id = r.toUserId ) as toUserNickName" +
            " from Reply r" +
            " where r.wishCardId = ?1")
    List<Object[]> getWishCardComment(Integer wishCardId);

}
