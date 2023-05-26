package cn.itcast.feign.clients.business;

import cn.itcast.feign.config.FeignConfiguration;
import cn.itcast.feign.pojo.business.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("businessService")
////@FeignClient(name="businessService",configuration = {FeignConfiguration.class})
//@FeignClient(name="businessService",url="https://businessService",configuration = {FeignConfiguration.class})
public interface BusinessClient {

    //操作订单信息
    @RequestMapping("business/orders/id/{id}")
    Orders findOrdersById(@PathVariable("id")int id);

    @RequestMapping("business/orders/all")
    List<Orders> getAllOrders();

    @RequestMapping("business/orders/insert")
    void insertOrders(@RequestBody Orders orders);

    @RequestMapping("business/orders/userId/{id}")
    List<String> getOrdersByUserId(@PathVariable("id") int id);

    @RequestMapping("business/orders/update")
    void updateOrders(@RequestBody Orders orders);

    @RequestMapping("business/orders/delete/id/{id}")
    void deleteOrdersById(@PathVariable("id")int id);


    //操作商品信息
    @RequestMapping("business/goods/all")
    List<Goods> getAllGoods();

    @RequestMapping("business/goods/id/{id}")
    Goods getGoodsById(@PathVariable("id") int id);


    @RequestMapping("business/goods/businessId/{id}")
    List<Goods> getGoodsByBusinessId(@PathVariable("id") int id);

    @RequestMapping("business/goods/insert")
    void insetGoods(@RequestBody Goods goods);


    @RequestMapping("business/goods/update")
    void updateGoods(@RequestBody Goods goods);

    @RequestMapping("business/goods/delete/id/{id}")
    void deleteGoodsById(@PathVariable("id") int id);

    @RequestMapping("business/goods/delete/businessId/{id}")
    void deleteGoodsByBusinessId(@PathVariable("id") int id);




    //操作店主信息
    @RequestMapping("business/businessUser/all")
    List<Business_user> getAll();


    @RequestMapping("business/businessUser/id/{id}")
    Business_user getById(@PathVariable("id") int id);


    @RequestMapping("business/businessUser/phone/{phone}")
    Business_user getByPhone(@PathVariable("phone") int phone);


    @RequestMapping("business/businessUser/update")
    void update(@RequestBody Business_user business);

    @RequestMapping("business/businessUser/insert")
    void insert(@RequestBody Business_user business);

    @RequestMapping("business/businessUser/delete/id/{id}")
    void deleteById(@PathVariable("id") int id);

    @RequestMapping("business/businessUser/delete/phone/{phone}")
    void deleteByPhone(@PathVariable("phone") int phone);


    //操作店铺信息

    @RequestMapping("business/business/all")
    List<Business> getAllBusiness();

    @RequestMapping("business/business/id/{id}")
    Business getBusinessById(@PathVariable("id") int id);

    @RequestMapping("business/business/userId/{id}")
    Business getBusinessByUserId(@PathVariable("id") int id);

    @RequestMapping("business/business/update")
    void updateBusiness(@RequestBody Business business);
    @RequestMapping("business/business/insert")
    void insertBusiness(@RequestBody Business business);

    @RequestMapping("business/business/delete/id/{id}")
    void deleteBusinessById(@PathVariable("id")int id);

    @RequestMapping("business/business/delete/userId/{id}")
    void deleteBusinessByUserId(@PathVariable("id") int userId);



    //操作账号信息
    @RequestMapping("business/allUser/id/{id}")
    AllUser getUserById(@PathVariable("id") int id);

    @RequestMapping("business/allUser/all")
    List<AllUser> getAllUser();

    @RequestMapping("business/allUser/username/{username}")
    AllUser getUserByUsername(@PathVariable("username") String username);

    @RequestMapping("business/allUser/update/{username}/{newPassword}")
    AllUser updateUserPassword(@PathVariable("username") String username,@PathVariable("newPassword") String newPassword);

    @RequestMapping("business/allUser/insert/{username}/{password}")
    AllUser insertUser(@PathVariable("username") String username,@PathVariable("password") String password);

    @RequestMapping("business/allUser/delete/id/{id}")
    AllUser deleteUserById(@PathVariable("id") int id);

    @RequestMapping("business/allUser/delete/username/{username}")
    AllUser deleteUserByUsername(@PathVariable("username") String username);
}
