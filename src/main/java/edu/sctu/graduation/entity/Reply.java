package edu.sctu.graduation.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhengsenwen on 2018/4/10.
 */

@Entity
@Table(name = "reply")
public class Reply implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer replyId;
    private String pubdate;
    private String description;
    private Integer wishCardId;
    private Integer fromUserId;
    private Integer toUserId;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWishCardId() {
        return wishCardId;
    }

    public void setWishCardId(Integer wishCardId) {
        this.wishCardId = wishCardId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }
}
