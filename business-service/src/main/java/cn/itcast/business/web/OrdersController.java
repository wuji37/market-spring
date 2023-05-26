package cn.itcast.business.web;

import cn.itcast.business.encryption.AesEncryption;
import cn.itcast.business.encryption.EncryptionConfig;
import cn.itcast.business.encryption.RSAEncryption;
import cn.itcast.business.pojo.Orders;
import cn.itcast.business.service.OrdersService;
import cn.itcast.feign.clients.user.UserClient;
import cn.itcast.feign.pojo.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("business/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private AesEncryption aesEncryption;

    @Autowired
    private EncryptionConfig encryptionConfig;

    @Autowired
    private UserClient userClient;

    @Autowired
    private RSAEncryption rsaEncryption;

    @Autowired
    private Environment environment;

    @RequestMapping("all")
    public List<Orders> getAllOrders(){
        return ordersService.getAllOrders();
    }

//    @RequestMapping("id/{id}")
//    public Orders getOrdersById(@PathVariable("id") int id){
//        return ordersService.getOrdersById(id);
//    }

    @RequestMapping("feign/id/{id}")
    public Orders getOrdersByIdFeing(@PathVariable("id") int id) throws JsonProcessingException {
        Orders orders=ordersService.getOrdersById(id);
        String s=userClient.findUserById(orders.getUserId());
        ObjectMapper objectMapper = new ObjectMapper();

        String str=null;
        try {
            str=aesEncryption.decrypt(s,encryptionConfig.key);
        } catch (Exception e) {
            System.out.println(e);
        }

        User user=objectMapper.readValue(str, User.class);
        System.out.println("user:"+user);

        orders.setUser(user);
        return orders;
    }

    @RequestMapping("id/{id}")
    public Orders getOrdersById(@PathVariable("id") int id) throws JsonProcessingException {
        Orders orders=ordersService.getOrdersById(id);
        return orders;
    }


    //对接受的用户信息数据进行解密，返回未加密的订单信息
    @RequestMapping("AES/id/{id}")
    public Orders getOrdersByIdAES(@PathVariable("id") int id) throws JsonProcessingException {
        Orders orders=ordersService.getOrdersById(id);
        String s=userClient.getUserById(orders.getUserId());
        ObjectMapper objectMapper = new ObjectMapper();

        String str=null;
        try {
            str=aesEncryption.decrypt(s,encryptionConfig.key);
        } catch (Exception e) {
            System.out.println(e);
        }

        User user=objectMapper.readValue(str, User.class);
        System.out.println("user:"+user);

        orders.setUser(user);
        return orders;
    }


    //对用户信息进行RSA解密，然后返回未加密的订单数据
    @RequestMapping("RSA/id/{id}")
    public Orders getOrdersByIdRSA(@PathVariable("id") int id) throws JsonProcessingException {
        Orders orders=ordersService.getOrdersById(id);
        String s=userClient.findUserById(orders.getUserId());
        ObjectMapper objectMapper = new ObjectMapper();

        String modulus=environment.getProperty("resPrivateKey.modulus");
        String exponent=environment.getProperty("resPrivateKey.exponent");

        System.out.println(modulus);
        System.out.println(exponent);

        PrivateKey privateKey=rsaEncryption.getPrivateKey(modulus,exponent);

        String str=null;
        try {
            str=rsaEncryption.decrypt(s,privateKey);
            System.out.println(str);
        } catch (Exception e) {
            System.out.println(e);
        }

        User user=objectMapper.readValue(str, User.class);
        System.out.println("user:"+user);

        orders.setUser(user);
        return orders;
    }


    @RequestMapping("userId/{id}")
    public List<String> getOrdersByUserId(@PathVariable("id")int id){

        List<Orders> list=ordersService.getOrdersByUserId(id);
        List<String> list1=new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for(Orders orders:list){
            String str=null;
            try {
                str=aesEncryption.encrypt(objectMapper.writeValueAsString(orders),encryptionConfig.key);
            } catch (Exception e) {
                System.out.println(e);
            }

            list1.add(str);
        }

        return list1;
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
