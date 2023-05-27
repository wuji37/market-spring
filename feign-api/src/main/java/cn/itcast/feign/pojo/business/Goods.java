package cn.itcast.feign.pojo.business;

import lombok.Data;


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

}
