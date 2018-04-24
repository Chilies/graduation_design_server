package edu.sctu.graduation.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhengsenwen on 2018/3/22.
 */
@Entity
@Table(name="avatar")
public class Avatar  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer avatarId;
    private Integer userId;
    private String avatarSrc;


    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }
}
