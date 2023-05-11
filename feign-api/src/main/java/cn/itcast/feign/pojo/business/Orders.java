package cn.itcast.feign.pojo.business;

import cn.itcast.feign.pojo.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Orders {
    private int id;
    private float price;
    private int businessId;
    private int goodsId;
    private int userId;
    private LocalDateTime startTime;
    private int unsubscribe;
    private float score;

    private Goods goods;
    private User user;
}
