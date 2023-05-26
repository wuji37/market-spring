package cn.itcast.feign.pojo.business;

import cn.itcast.feign.pojo.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.Data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
