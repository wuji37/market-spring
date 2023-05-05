package cn.itcast.business.mapper;

import cn.itcast.business.pojo.AllUser;
import cn.itcast.business.pojo.Business_user;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Blob;
import java.util.List;

public interface Business_userMapper {

    @Select("select * from business_user")
    List<Business_user> getAllUser();

    @Select("select * from business_user where id=#{id}")
    Business_user getUserById(int id);

    @Select("select * from business_user where phone=#{phone}")
    Business_user getUserByPhone(int phone);

    @Update("update business_user set name=#{name},phone=#{phone},age=#{age},address=#{address},photo=#{photo} where id=#{id}")
    void update(String name, int phone, int age, String address, Blob photo,int id);

    @Insert("insert into business_user(id,name,phone,age,address,photo) values(null,#{name},#{phone},#{age},#{address},#{photo})")
    void insert(String name, int phone, int age, String address, Blob photo);

    @Delete("delete from business_user where id=#{id}")
    void deleteById(int id);

    @Delete("delete from business_user where phone=#{phone}")
    void deleteByPhone(int phone);

}
