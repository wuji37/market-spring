package cn.itcast.business.mapper;


import cn.itcast.business.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdersMapper {
    @Select("select * from orders")
    List<Orders> getAllOrders();

    @Select("select * from orders where id=#{id}")
    Orders getOrdersById(int id);

    @Select("select * from orders where userId=#{id}")
    List<Orders> getOrdersByUserId(int id);

    @Select("select * from orders where businessId=#{id}")
    List<Orders> getOrdersByBusinessId(int id);

    @Insert("insert into orders(id,price,businessId,goodsId,userId,startTime,unsubscribe,score) values(null,#{price},#{businessId},#{goodsId},#{userId},#{startTime},#{unsubscribe},#{score})")
    void insert(float price, int businessId, int goodsId, int userId, LocalDateTime startTime, int unsubscribe, float score);

    @Update("update orders set price=#{price},businessId=#{businessId},goodsId=#{goodsId},userId=#{userId},startTime=#{startTime},unsubscribe=#{unsubscribe},score=#{score} where id=#{id}")
    void update(float price,int businessId, int goodsId, int userId, LocalDateTime startTime,int unsubscribe,float score,int id);

    @Delete("delete from orders where id=#{id}")
    void deleteById(int id);

    @Delete("delete from orders where BusinessId=#{id}")
    void deleteByBusinessId(int id);

    @Delete("delete from orders where UserId=#{id}")
    void deleteByUserId(int id);

    @Delete("delete from orders where goodsId=#{id}")
    void deleteByGoodsId(int id);
}
