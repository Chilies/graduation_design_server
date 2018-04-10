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


    //    select u.id,u.nick_name,av.avatar_src,wc.wish_card_id,wc.create_time,wc.description,wc.price,wc.type,wci.wish_card_img_src
//    from all_user u, avatar av,wish_card wc,wish_card_img wci
//    where u.id = av.user_id
//    and wc.wish_card_id = wci.wish_card_id
//    order by wc.createTime desc
    @Query("SELECT u.id,u.nickName,av.avatarSrc,wc.wishCardId,wc.createTime,wc.description,wc.price,wc.type,wci.wishCardImgSrc " +
            "FROM User u,Avatar av,WishCard wc,WishCardImg wci " +
            "where u.id = av.userId AND u.id = wc.userId AND wc.wishCardId = wci.wishCardId" +
            " order by wc.createTime desc")
    public List<Object[]> getAllWish();


    //    select u.id,u.nick_name,av.avatar_src,wc.wish_card_id,wc.create_time,wc.description,wc.price,wc.type,wci.wish_card_img_src
//    from all_user u, avatar av,wish_card wc,wish_card_img wci
//    where u.id = av.user_id
//    and wc.wish_card_id = wci.wish_card_id
//    and wc.wish_card_id = 24
    @Query("SELECT u.id,u.nickName,av.avatarSrc,wc.wishCardId,wc.createTime,wc.description,wc.price,wc.type,wci.wishCardImgSrc " +
            "FROM User u,Avatar av,WishCard wc,WishCardImg wci " +
            "where u.id = av.userId AND u.id = wc.userId AND wc.wishCardId = wci.wishCardId" +
            " AND wc.wishCardId = ?1")
    public List<Object[]> getOneWishCard(int wishCardId);

}
