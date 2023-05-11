package cn.itcast.feign.pojo.user;

import cn.itcast.feign.pojo.business.Orders;
import lombok.Data;

import java.sql.Blob;
import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private int phone;
    private int age;
    private String address;
    private String photo;
    private String sex;

}
