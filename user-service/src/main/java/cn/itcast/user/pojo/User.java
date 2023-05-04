package cn.itcast.user.pojo;

import lombok.Data;

import java.sql.Blob;

@Data
public class User {
    private int id;
    private String name;
    private int phone;
    private int age;
    private String address;
    private Blob photo;
    private String sex;
}
