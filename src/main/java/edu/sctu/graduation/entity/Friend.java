package edu.sctu.graduation.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhengsenwen on 2018/4/14.
 */

@Entity
@Table(name = "friend")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer fellowStatus;
    private Integer friendId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFellowStatus() {
        return fellowStatus;
    }

    public void setFellowStatus(Integer fellowStatus) {
        this.fellowStatus = fellowStatus;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}
