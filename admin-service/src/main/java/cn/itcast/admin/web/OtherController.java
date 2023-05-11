package cn.itcast.admin.web;

import cn.itcast.feign.clients.business.BusinessClient;
import cn.itcast.feign.pojo.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class OtherController {

    @Autowired
    private BusinessClient businessClient;


    //操作订单信息
    @RequestMapping("orders/id/{id}")
    public Orders findOrdersById(@PathVariable("id")int id){
        return businessClient.findOrdersById(id);
    }

    @RequestMapping("orders/all")
    public List<Orders> getAllOrders(){
        return businessClient.getAllOrders();
    }

    @RequestMapping("orders/insert")
    public void insertOrders(@RequestBody Orders orders){
        businessClient.insertOrders(orders);
    }

    @RequestMapping("orders/update")
    public void updateOrders(@RequestBody Orders orders){
        businessClient.updateOrders(orders);
    }

    @RequestMapping("orders/delete/id/{id}")
    public void deleteOrdersById(@PathVariable("id")int id){
        businessClient.deleteOrdersById(id);
    }


    //操作商品信息
    @RequestMapping("goods/all")
    public List<Goods> getAllGoods(){
        return businessClient.getAllGoods();
    }

    @RequestMapping("goods/id/{id}")
    public Goods getGoodsById(@PathVariable("id") int id){
        return businessClient.getGoodsById(id);
    }


    @RequestMapping("goods/businessId/{id}")
    public List<Goods> getGoodsByBusinessId(@PathVariable("id") int id){
        return businessClient.getGoodsByBusinessId(id);
    }

    @RequestMapping("goods/insert")
    public void insetGoods(@RequestBody Goods goods){
        businessClient.insetGoods(goods);
    }


    @RequestMapping("goods/update")
    public void updateGoods(@RequestBody Goods goods){
        businessClient.updateGoods(goods);
    }

    @RequestMapping("goods/delete/id/{id}")
    public void deleteGoodsById(@PathVariable("id") int id){
        businessClient.deleteGoodsById(id);
    }

    @RequestMapping("goods/delete/businessId/{id}")
    public void deleteGoodsByBusinessId(@PathVariable("id") int id){
        businessClient.deleteGoodsByBusinessId(id);
    }




    //操作店主信息
    @RequestMapping("businessUser/all")
    public List<Business_user> getAll(){
        return businessClient.getAll();
    }


    @RequestMapping("businessUser/id/{id}")
    public Business_user getById(@PathVariable("id") int id){
        return businessClient.getById(id);
    }


    @RequestMapping("businessUser/phone/{phone}")
    public Business_user getByPhone(@PathVariable("phone") int phone){
        return businessClient.getByPhone(phone);
    }


    @RequestMapping("businessUser/update")
    public void update(@RequestBody Business_user business){
        businessClient.update(business);
    }

    @RequestMapping("businessUser/insert")
    public void insert(@RequestBody Business_user business){
        businessClient.insert(business);
    }

    @RequestMapping("businessUser/delete/id/{id}")
    public void deleteById(@PathVariable("id") int id){
        businessClient.deleteById(id);
    }

    @RequestMapping("businessUser/delete/phone/{phone}")
    public void deleteByPhone(@PathVariable("phone") int phone){
        businessClient.deleteByPhone(phone);
    }


    //操作店铺信息

    @RequestMapping("business/all")
    public List<Business> getAllBusiness(){
        return businessClient.getAllBusiness();
    }

    @RequestMapping("business/id/{id}")
    public Business getBusinessById(@PathVariable("id") int id){
        return businessClient.getBusinessById(id);
    }

    @RequestMapping("business/userId/{id}")
    public Business getBusinessByUserId(@PathVariable("id") int id){
        return businessClient.getBusinessByUserId(id);
    }

    @RequestMapping("business/update")
    public void updateBusiness(@RequestBody Business business){
        businessClient.updateBusiness(business);
    }

    @RequestMapping("business/insert")
    public void insertBusiness(@RequestBody Business business){
        businessClient.insertBusiness(business);
    }

    @RequestMapping("business/delete/id/{id}")
    public void deleteBusinessById(@PathVariable("id")int id){
        businessClient.deleteBusinessById(id);
    }

    @RequestMapping("business/delete/userId/{id}")
    public void deleteBusinessByUserId(@PathVariable("id") int userId){
        businessClient.deleteBusinessByUserId(userId);
    }



    //操作账号信息
    @RequestMapping("allUser/id/{id}")
    public AllUser getUserById(@PathVariable("id") int id){
        return businessClient.getUserById(id);
    }

    @RequestMapping("allUser/all")
    public List<AllUser> getAllUser(){
        return businessClient.getAllUser();
    }

    @RequestMapping("allUser/username/{username}")
    public AllUser getUserByUsername(@PathVariable("username") String username){
        return businessClient.getUserByUsername(username);
    }

    @RequestMapping("allUser/update/{username}/{newPassword}")
    public AllUser updateUserPassword(@PathVariable("username") String username,@PathVariable("newPassword") String newPassword){
        return businessClient.updateUserPassword(username,newPassword);
    }

    @RequestMapping("allUser/insert/{username}/{password}")
    public AllUser insertUser(@PathVariable("username") String username,@PathVariable("password") String password){
        return businessClient.insertUser(username,password);
    }

    @RequestMapping("allUser/delete/id/{id}")
    public AllUser deleteUserById(@PathVariable("id") int id){
        return businessClient.deleteUserById(id);
    }

    @RequestMapping("allUser/delete/username/{username}")
    public AllUser deleteUserByUsername(@PathVariable("username") String username){
        return  businessClient.deleteUserByUsername(username);
    }
}
