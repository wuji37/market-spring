package cn.itcast.feign.pojo.business;

import lombok.Data;

import java.sql.Blob;

@Data
public class Business {
    private int id;
    private int businessUserId;
    private String name;
    private String introduction;
    private float score;
    private String photo;

    private Business_user businessUser;
}
