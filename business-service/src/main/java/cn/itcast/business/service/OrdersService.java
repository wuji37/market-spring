package cn.itcast.business.service;

import cn.itcast.business.mapper.OrdersMapper;
import cn.itcast.business.pojo.Goods;
import cn.itcast.business.pojo.Orders;
import cn.itcast.feign.clients.user.UserClient;
import cn.itcast.feign.pojo.user.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserClient userClient;

    @Autowired

    public List<Orders> getAllOrders(){
        return ordersMapper.getAllOrders();
    }

    public Orders getOrdersById(int id){
        Orders orders=ordersMapper.getOrdersById(id);
//        Goods goods=goodsService.getGoodsById(orders.getGoodsId());
//        orders.setGoods(goods);

//        User user=userClient.findUserById(orders.getUserId());
//        orders.setUser(user);
        return orders;
    }

    public List<Orders> getOrdersByUserId(int id){
        return ordersMapper.getOrdersByUserId(id);
    }

    public List<Orders> getOrdersByBusinessId(int id){
        return ordersMapper.getOrdersByBusinessId(id);
    }

    public void insert(Orders orders){
        ordersMapper.insert(orders.getPrice(),orders.getBusinessId(),orders.getGoodsId(),orders.getUserId(),orders.getStartTime(),orders.getUnsubscribe(),orders.getScore());
    }

    public void update(Orders orders){
        ordersMapper.update(orders.getPrice(),orders.getBusinessId(),orders.getGoodsId(),orders.getUserId(),orders.getStartTime(),orders.getUnsubscribe(),orders.getScore(),orders.getId());
    }

    public void deleteById(int id){
        ordersMapper.deleteById(id);
    }

    public void deleteByBusinessId(int id){
        ordersMapper.deleteByBusinessId(id);
    }

    public void deleteByUserId(int id){
        ordersMapper.deleteByUserId(id);
    }

    public void deleteByGoodsId(int id){
        ordersMapper.deleteByGoodsId(id);
    }

}
