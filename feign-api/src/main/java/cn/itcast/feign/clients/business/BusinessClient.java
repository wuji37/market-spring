package cn.itcast.feign.clients.business;

import cn.itcast.feign.pojo.business.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("businessService")
public interface BusinessClient {

    //操作订单信息
    @RequestMapping("orders/id/{id}")
    Orders findOrdersById(@PathVariable("id")int id);

    @RequestMapping("orders/all")
    List<Orders> getAllOrders();

    @RequestMapping("orders/insert")
    void insertOrders(@RequestBody Orders orders);

    @RequestMapping("orders/userId/{id}")
    List<Orders> getOrdersByUserId(@PathVariable("id") int id);

    @RequestMapping("orders/update")
    void updateOrders(@RequestBody Orders orders);

    @RequestMapping("orders/delete/id/{id}")
    void deleteOrdersById(@PathVariable("id")int id);


    //操作商品信息
    @RequestMapping("goods/all")
    List<Goods> getAllGoods();

    @RequestMapping("goods/id/{id}")
    Goods getGoodsById(@PathVariable("id") int id);


    @RequestMapping("goods/businessId/{id}")
    List<Goods> getGoodsByBusinessId(@PathVariable("id") int id);

    @RequestMapping("goods/insert")
    void insetGoods(@RequestBody Goods goods);


    @RequestMapping("goods/update")
    void updateGoods(@RequestBody Goods goods);

    @RequestMapping("goods/delete/id/{id}")
    void deleteGoodsById(@PathVariable("id") int id);

    @RequestMapping("goods/delete/businessId/{id}")
    void deleteGoodsByBusinessId(@PathVariable("id") int id);




    //操作店主信息
    @RequestMapping("businessUser/all")
    List<Business_user> getAll();


    @RequestMapping("businessUser/id/{id}")
    Business_user getById(@PathVariable("id") int id);


    @RequestMapping("businessUser/phone/{phone}")
    Business_user getByPhone(@PathVariable("phone") int phone);


    @RequestMapping("businessUser/update")
    void update(@RequestBody Business_user business);

    @RequestMapping("businessUser/insert")
    void insert(@RequestBody Business_user business);

    @RequestMapping("businessUser/delete/id/{id}")
    void deleteById(@PathVariable("id") int id);

    @RequestMapping("businessUser/delete/phone/{phone}")
    void deleteByPhone(@PathVariable("phone") int phone);


    //操作店铺信息

    @RequestMapping("business/all")
    List<Business> getAllBusiness();

    @RequestMapping("business/id/{id}")
    Business getBusinessById(@PathVariable("id") int id);

    @RequestMapping("business/userId/{id}")
    Business getBusinessByUserId(@PathVariable("id") int id);

    @RequestMapping("business/update")
    void updateBusiness(@RequestBody Business business);
    @RequestMapping("business/insert")
    void insertBusiness(@RequestBody Business business);

    @RequestMapping("business/delete/id/{id}")
    void deleteBusinessById(@PathVariable("id")int id);

    @RequestMapping("business/delete/userId/{id}")
    void deleteBusinessByUserId(@PathVariable("id") int userId);



    //操作账号信息
    @RequestMapping("allUser/id/{id}")
    AllUser getUserById(@PathVariable("id") int id);

    @RequestMapping("allUser/all")
    List<AllUser> getAllUser();

    @RequestMapping("allUser/username/{username}")
    AllUser getUserByUsername(@PathVariable("username") String username);

    @RequestMapping("allUser/update/{username}/{newPassword}")
    AllUser updateUserPassword(@PathVariable("username") String username,@PathVariable("newPassword") String newPassword);

    @RequestMapping("allUser/insert/{username}/{password}")
    AllUser insertUser(@PathVariable("username") String username,@PathVariable("password") String password);

    @RequestMapping("allUser/delete/id/{id}")
    AllUser deleteUserById(@PathVariable("id") int id);

    @RequestMapping("allUser/delete/username/{username}")
    AllUser deleteUserByUsername(@PathVariable("username") String username);
}
