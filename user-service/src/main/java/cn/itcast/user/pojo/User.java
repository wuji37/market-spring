package cn.itcast.user.pojo;

import cn.itcast.feign.pojo.business.Orders;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private String phone;
    private int age;
    private String address;
    private String photo;
    private String sex;

}
