package cn.itcast.business.pojo;

import lombok.Data;

import java.sql.Blob;

@Data
public class Business_user {
    private int id;
    private String name;
    private int phone;
    private int age;
    private String address;
    private Blob photo;
}
