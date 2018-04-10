package edu.sctu.graduation.dao;

import edu.sctu.graduation.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhengsenwen on 2018/3/20.
 */
public interface TypeDao extends JpaRepository<Type, Integer> {

      @Query("select type from Type")
    public List<String> getAllType();

}
