package edu.sctu.graduation.dao;

import edu.sctu.graduation.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhengsenwen on 2018/4/8.
 */
public interface AreaDao  extends JpaRepository<Area, Integer> {

    @Query("select province from Area")
    public List<String> getArea();


    @Query("select id from Area where province = ?1")
    public String getAreaId(String province);

}
