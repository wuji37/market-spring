package cn.itcast.business.service;

import cn.itcast.business.mapper.Business_userMapper;
import cn.itcast.business.pojo.AllUser;
import cn.itcast.business.pojo.Business_user;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;

@Service
public class Business_userService {

    @Autowired
    private Business_userMapper business_userMapper;

    public List<Business_user> getAll(){
        return business_userMapper.getAllUser();
    }


    public Business_user getById(int id){
        return business_userMapper.getUserById(id);
    }


    public Business_user getByPhone(int phone){
        return business_userMapper.getUserByPhone(phone);
    }



    public void update(Business_user business){
        //String name, int phone, int age, String address, Blob photo
        business_userMapper.update(business.getName(), business.getPhone(), business.getAge(), business.getAddress(), business.getPhoto(), business.getId());
    }


    public void insert(Business_user business){
        business_userMapper.insert(business.getName(), business.getPhone(), business.getAge(), business.getAddress(), business.getPhoto());
    }


    public void deleteById(int id){
        business_userMapper.deleteById(id);
    }


    public void deleteByPhone(int phone){
        business_userMapper.deleteByPhone(phone);
    }
}
