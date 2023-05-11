package cn.itcast.business.web;

import cn.itcast.business.pojo.Business_user;
import cn.itcast.business.service.Business_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("business/businessUser")
public class Business_userController {

    @Autowired
    private Business_userService business_userService;

    @RequestMapping("all")
    public List<Business_user> getAll(){
        return business_userService.getAll();
    }


    @RequestMapping("id/{id}")
    public Business_user getById(@PathVariable("id") int id){
        return business_userService.getById(id);
    }


    @RequestMapping("phone/{phone}")
    public Business_user getByPhone(@PathVariable("phone") int phone){
        return business_userService.getByPhone(phone);
    }


    @RequestMapping("update")
    public void update(@RequestBody Business_user business){
        //String name, int phone, int age, String address, Blob photo
        business_userService.update(business);
    }

    @RequestMapping("insert")
    public void insert(@RequestBody Business_user business){
        business_userService.insert(business);
    }

    @RequestMapping("delete/id/{id}")
    public void deleteById(@PathVariable("id") int id){
        business_userService.deleteById(id);
    }

    @RequestMapping("delete/phone/{phone}")
    public void deleteByPhone(@PathVariable("phone") int phone){
        business_userService.deleteByPhone(phone);
    }
}
