package cn.itcast.user.web;

import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping(value = "user",produces = "application/json;charset=utf-8")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("all")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @RequestMapping("{id}")
    public User getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @RequestMapping(path="insert",method = RequestMethod.POST)
    public void insertUser(@RequestBody User user) throws UnsupportedEncodingException {
        userService.insertUser(user);
    }

    @RequestMapping(path="update",method=RequestMethod.POST)
    public void updateUser(@RequestBody User user) throws UnsupportedEncodingException {
        userService.updateUserById(user);
    }

    @RequestMapping(path = "delete/{id}",method=RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") int id){
        userService.deleteUserById(id);
    }
}
