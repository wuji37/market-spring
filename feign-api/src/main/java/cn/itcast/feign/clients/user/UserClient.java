package cn.itcast.feign.clients.user;

import cn.itcast.feign.pojo.user.AllUser;
import cn.itcast.feign.pojo.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@FeignClient("userService")
public interface UserClient {


    //操作用户信息
    @GetMapping("user/user/{id}")
    User findUserById(@PathVariable("id") int id);

    @RequestMapping("user/user/all")
    List<User> getAllUser();

    @RequestMapping("user/user/{id}")
    User getUserById(@PathVariable("id") int id);

    @RequestMapping(path="user/user/insert",method = RequestMethod.POST)
    void insertUser(@RequestBody User user);

    @RequestMapping(path="user/user/update",method=RequestMethod.POST)
    void updateUser(@RequestBody User user);

    @RequestMapping(path = "user/user/delete/{id}",method=RequestMethod.DELETE)
    void deleteUserById(@PathVariable("id") int id);



    //操作用户账号信息
    @RequestMapping("user/allUser/id/{id}")
    AllUser getAllUserById(@PathVariable("id") int id);

    @RequestMapping("user/allUser/all")
    List<AllUser> getAllAllUser();

    @RequestMapping("user/allUser/username/{username}")
    AllUser getAllUserByUsername(@PathVariable("username") String username);

    @RequestMapping("user/allUser/update/{username}/{newPassword}")
    AllUser updateAllUserPassword(@PathVariable("username") String username,@PathVariable("newPassword") String newPassword);

    @RequestMapping("user/allUser/insert/{username}/{password}")
    AllUser insertAllUser(@PathVariable("username") String username,@PathVariable("password") String password);

    @RequestMapping("user/allUser/delete/id/{id}")
    AllUser deleteAllUserById(@PathVariable("id") int id);

    @RequestMapping("user/allUser/delete/username/{username}")
    AllUser deleteUserByUsername(@PathVariable("username") String username);
}
