package cn.itcast.user.web;

import cn.itcast.feign.clients.business.BusinessClient;
import cn.itcast.feign.pojo.business.Orders;
import cn.itcast.user.encryption.AesEncryption;
import cn.itcast.user.encryption.EncryptionConfig;
import cn.itcast.user.encryption.RSAEncryption;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "user/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BusinessClient businessClient;

    @Autowired
    private EncryptionConfig encryptionConfig;

    @Autowired
    private AesEncryption aesEncryption;

    @Autowired
    private RSAEncryption rsaEncryption;

    @Autowired
    private Environment environment;
    @RequestMapping("all")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }


    //访问所有用户进行AES数据加密
    @RequestMapping("AES/all")
    public List<String> getAllUser1(){

        System.out.println(encryptionConfig.key);
        List<User> userList = userService.getAllUser();
        System.out.println(userList);
        List<String> encryptedUserList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();


        for (User user : userList) {
            String encryptedUser = "";
            try {
               encryptedUser=aesEncryption.encrypt(objectMapper.writeValueAsString(user),encryptionConfig.key);
            } catch (Exception e) {
                System.out.println(e);
            }
            encryptedUserList.add(encryptedUser);
        }

//
//        List<User> list=new ArrayList<>();
//        for(String s:encryptedUserList){
//            String str=null;
//            try {
//                str=aesEncryption.decrypt(s,encryptionConfig.key);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//            //使用json库进行反序列化
//            User user = objectMapper.readValue(str, User.class);
//            list.add(user);
//        }
//
//        System.out.println(list);

        return encryptedUserList;
//        return list;
    }


    @RequestMapping("{id}")
    public User getUserById2(@PathVariable("id") int id){
        System.out.println(id);
        return userService.getUserById(id);
    }

    //返回单个用户的AES加密后的数据
    @RequestMapping("AES/{id}")
    public String getUserById1(@PathVariable("id") int id){
        User user=userService.getUserById(id);
        ObjectMapper objectMapper=new ObjectMapper();

        String s = "";
        try {
            s=aesEncryption.encrypt(objectMapper.writeValueAsString(user),encryptionConfig.key);
        } catch (Exception e) {
            System.out.println(e);
        }

        return s;
    }

    //使用RSA进行数据加密
    @RequestMapping("RSA/{id}")
    public String getUserById(@PathVariable("id") int id) throws Exception {
        User user=userService.getUserById(id);
        ObjectMapper objectMapper=new ObjectMapper();

        String modulus=environment.getProperty("resPublicKey.modulus");
        String exponent=environment.getProperty("resPublicKey.exponent");


        PublicKey publicKey=rsaEncryption.getPublicKey(modulus,exponent);

        String s = "";
        try {
            s=rsaEncryption.encrypt(objectMapper.writeValueAsString(user),publicKey);
        } catch (Exception e) {
            System.out.println(e);
        }

        return s;
    }

    @RequestMapping(path="insert",method = RequestMethod.POST)
    public void insertUser(@RequestBody User user){
        userService.insertUser(user);
    }

    @RequestMapping(path="update",method=RequestMethod.POST)
    public void updateUser(@RequestBody User user){
        userService.updateUserById(user);
    }

    @RequestMapping(path = "delete/{id}",method=RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") int id){
        userService.deleteUserById(id);
    }


//    @RequestMapping("getOrders/{userId}")
//    public List<String> getOrdersByUserId(@PathVariable("userId")int userId){
//
//        List<Orders> list=businessClient.getOrdersByUserId(userId);
//        List<String> list1=new ArrayList<>();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        for(Orders orders:list){
//            String str=null;
//            try {
//                str=aesEncryption.encrypt(objectMapper.writeValueAsString(orders),encryptionConfig.key);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//            list1.add(str);
//        }
//
//        return list1;
//    }

    @RequestMapping("getOrders/{userId}")
    public List<Orders> getOrdersByUserId(@PathVariable("userId")int userId){

        List<Orders> list=businessClient.getOrdersByUserId(userId);

        return list;
    }

    @RequestMapping("getOrders/AES/{userId}")
    public List<String> getOrdersByUserId1(@PathVariable("userId")int userId){

        List<String> list=businessClient.getOrdersByUserIdAES(userId);

        return list;
    }

}
