package edu.sctu.graduation.dao;

import edu.sctu.graduation.entity.WishCard;
import edu.sctu.graduation.json.WishCardContentJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhengsenwen on 2018/3/19.
 */
public interface WishCardDao extends JpaRepository<WishCard, Integer> {

    @Query("from WishCard where id=?1")
    public WishCard getWishCardById(int id);

//    select u.id,u.nick_name,
//            (select a.avatar_src from avatar a where a.user_id = u.id) as avatarSrc,
//    wc.wish_card_id,wc.create_time,wc.description,wc.price,wc.type,
//    wci.wish_card_img_src,ali.alipay_receive_code
//    from all_user u, wish_card wc,wish_card_img wci,alipay_transfer ali
//    where wc.wish_card_id = wci.wish_card_id
//    and u.id = ali.user_id
//    order by wc.create_time desc
    @Query("SELECT u.id,u.nickName," +
            "(select a.avatarSrc from Avatar a where a.userId = u.id) as avatarSrc," +
            " wc.wishCardId,wc.createTime,wc.description," +
            " wc.price,wc.type,wci.wishCardImgSrc,ali.alipayReceiveCode" +
            " FROM User u,WishCard wc,WishCardImg wci,Alipay ali " +
            " WHERE u.id = wc.userId " +
            " AND wc.wishCardId = wci.wishCardId" +
            " AND u.id = ali.userId" +
            " order by wc.createTime desc")
    public List<Object[]> getAllWish();


//    select u.id,u.nick_name,
//            (select a.avatar_src from avatar a where a.user_id = u.id) as avatarSrc,
//    wc.wish_card_id,wc.create_time,wc.description,
//    wc.price,wc.type,wci.wish_card_img_src,ali.alipay_receive_code
//    from all_user u, wish_card wc,wish_card_img wci,alipay_transfer ali
//    where wc.wish_card_id = wci.wish_card_id
//    and u.id = ali.user_id
//    and wc.wish_card_id = 28
    @Query("SELECT u.id,u.nickName," +
            "(select a.avatarSrc from Avatar a where a.userId = u.id) as avatarSrc," +
            "wc.wishCardId,wc.createTime,wc.description," +
            " wc.price,wc.type,wci.wishCardImgSrc,ali.alipayReceiveCode" +
            " FROM User u,WishCard wc,WishCardImg wci,Alipay ali " +
            " WHERE u.id = wc.userId " +
            " AND wc.wishCardId = wci.wishCardId" +
            " AND u.id = ali.userId" +
            " AND wc.wishCardId = ?1")
    public List<Object[]> getOneWishCard(int wishCardId);

}
