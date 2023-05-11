package cn.itcast.business.pojo;

import cn.itcast.feign.pojo.user.User;
import lombok.Data;

import java.util.List;


@Data
public class Goods {
    private int id;
    private int businessId;
    private String photo;
    private String name;
    private String introduction;
    private float price;
    private float discount;
    private int sale;
    private float score;

    private Business business;
}
