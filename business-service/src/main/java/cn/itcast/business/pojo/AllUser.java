package cn.itcast.business.pojo;

import lombok.Data;

@Data
public class AllUser {
    private int id;
    private String username;
    private String password;

    private Business_user businessUser;

}