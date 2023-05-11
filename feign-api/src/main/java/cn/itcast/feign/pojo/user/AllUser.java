package cn.itcast.feign.pojo.user;

import lombok.Data;


@Data
public class AllUser {
    private int id;
    private String username;
    private String password;

    private User user;
}
