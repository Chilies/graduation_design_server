package edu.sctu.graduation.dao;

import edu.sctu.graduation.entity.WishCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zhengsenwen on 2018/3/19.
 */
public interface WishCardDao extends JpaRepository<WishCard, Integer> {

    @Query("from WishCard where id=?1")
    public WishCard getWishCardById(int id);
}
