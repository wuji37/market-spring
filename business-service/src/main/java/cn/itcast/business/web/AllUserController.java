package cn.itcast.business.web;

import cn.itcast.business.pojo.AllUser;
import cn.itcast.business.service.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("allUser")
public class AllUserController {
    @Autowired
    private AllUserService allUserService;

    @RequestMapping("id/{id}")
    public AllUser getUserById(@PathVariable("id") int id){
        return allUserService.getUserById(id);
    }

    @RequestMapping("all")
    public List<AllUser> getAllUser(){
        return allUserService.getAllUser();
    }

    @RequestMapping("username/{username}")
    public AllUser getUserByUsername(@PathVariable("username") String username){
        return allUserService.getUserByUsername(username);
    }

    @RequestMapping("update/{username}/{newPassword}")
    public AllUser updateUserPassword(@PathVariable("username") String username,@PathVariable("newPassword") String newPassword){
        return allUserService.updateUserPassword(username, newPassword);
    }

    @RequestMapping("insert/{username}/{password}")
    public AllUser insertUser(@PathVariable("username") String username,@PathVariable("password") String password){
        return allUserService.insertUser(username,password);
    }

    @RequestMapping("delete/id/{id}")
    public AllUser deleteUserById(@PathVariable("id") int id){
        return allUserService.deleteUserById(id);
    }

    @RequestMapping("delete/username/{username}")
    public AllUser deleteUserByUsername(@PathVariable("username") String username){
        return allUserService.deleteUserByUsername(username);
    }
}
