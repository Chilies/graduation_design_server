package edu.sctu.graduation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by zhengsenwen on 2018/4/8.
 */
@Entity
@Table(name="area")
public class Area  implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private String province;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
