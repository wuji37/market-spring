package cn.itcast.business.pojo;

import lombok.Data;

import java.sql.Blob;

@Data
public class Business {
    private int id;
    private int business_user_id;
    private String name;
    private String introduction;
    private float score;
    private Blob photo;
}
