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

    @Query("SELECT u.id,u.nickName,av.avatarSrc,wc.wishCardId,wc.createTime,wc.description,wc.type,wci.wishCardImgSrc " +
            "FROM User u,Avatar av,WishCard wc,WishCardImg wci " +
            "where u.id = av.userId AND u.id = wc.userId AND wc.wishCardId = wci.wishCardId" +
            " order by wc.createTime desc")
    public List<Object[]> getAllWish();

}
