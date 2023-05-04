package cn.itcast.user.service;

import cn.itcast.user.mapper.UserMapper;
import cn.itcast.user.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

    public User getUserById(int id){
        return userMapper.getUserById(id);
    }


    public void insertUser(User user){
        userMapper.insertUser(user.getName(),user.getPhone(),user.getSex(),user.getAge(),user.getAddress());
    }

    public void deleteUserById(int id){
        userMapper.deleteUserById(id);
    }

    public void updateUserById(User user){
        userMapper.updateUserById(user.getName(),user.getPhone(),user.getSex(),user.getAge(),user.getAddress(),user.getId());
    }
}
