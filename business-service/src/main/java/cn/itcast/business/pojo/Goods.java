package cn.itcast.business.pojo;

import lombok.Data;

import java.sql.Blob;

@Data
public class Goods {
    private int id;
    private int businessId;
    private Blob photo;
    private String name;
    private String introduction;
    private float price;
    private float discount;
    private int sale;
    private float score;

    private Business business;
}
