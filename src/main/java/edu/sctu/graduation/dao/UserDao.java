package edu.sctu.graduation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.sctu.graduation.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

    @Query("from User where id=?1")
    public User getUserById(String id);

    @Query("from User where telephone=?1")
    public User getUserByPhone(String telephone);

    @Query("from User where telephone=?1 and pwd=?2")
    public User login(String phone, String password);

}
