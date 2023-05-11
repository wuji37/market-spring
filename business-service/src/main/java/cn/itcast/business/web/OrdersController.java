package cn.itcast.business.web;

import cn.itcast.business.pojo.Orders;
import cn.itcast.business.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("business/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("all")
    public List<Orders> getAllOrders(){
        return ordersService.getAllOrders();
    }

    @RequestMapping("id/{id}")
    public Orders getOrdersById(@PathVariable("id") int id){
        return ordersService.getOrdersById(id);
    }

    @RequestMapping("userId/{id}")
    public List<Orders> getOrdersByUserId(@PathVariable("id")int id){
        return ordersService.getOrdersByUserId(id);
    }

    @RequestMapping("businessId/{id}")
    public List<Orders> getOrdersByBusinessId(@PathVariable("id")int id){
        return ordersService.getOrdersByBusinessId(id);
    }

    @RequestMapping("insert")
    public void insert(@RequestBody Orders orders){
        //float price, int businessId, int goodsId, int userId, Time startTime, int unsubscribe, float score
        ordersService.insert(orders);
    }

    @RequestMapping("update")
    public void update(@RequestBody Orders orders){
        ordersService.update(orders);
    }

    @RequestMapping("delete/id/{id}")
    public void deleteById(@PathVariable("id")int id){
        ordersService.deleteById(id);
    }

    @RequestMapping("delete/businessId/{id}")
    public void deleteByBusinessId(@PathVariable("id")int id){
        ordersService.deleteByBusinessId(id);
    }

    @RequestMapping("delete/userId/{id}")
    public void deleteByUserId(@PathVariable("id")int id){
        ordersService.deleteByUserId(id);
    }

    @RequestMapping("delete/goodsId/{id}")
    public void deleteByGoodsId(@PathVariable("id")int id){
        ordersService.deleteByGoodsId(id);
    }
}
