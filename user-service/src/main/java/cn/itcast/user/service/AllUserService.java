package cn.itcast.user.service;

import cn.itcast.user.mapper.AllUserMapper;
import cn.itcast.user.pojo.AllUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllUserService {

    @Autowired
    private AllUserMapper allUserMapper;

    public List<AllUser> getAllUser(){
        return allUserMapper.getAllUser();
    }

    public AllUser getUserById(int id){
        return allUserMapper.getUserById(id);
    }

    public AllUser getUserByUsername(String username){
        return allUserMapper.getUserByUsername(username);
    }

    //更新密码
    public AllUser updateUserPassword(String username,String newPassword){
        allUserMapper.updateUserPasswordByUsername( username, newPassword);
        return allUserMapper.getUserByUsername(username);
    }

    //注册用户
    public AllUser insertUser(String password,String username){
        allUserMapper.insertUser(password, username);
        return allUserMapper.getUserByUsername(username);
    }

    //删除账号密码
    public AllUser deleteUserById(int id){
        allUserMapper.deleteUserById(id);
        return null;
    }

    //删除账号密码
    public AllUser deleteUserByUsername(String username){
        allUserMapper.deleteUserByUsername(username);
        return null;
    }
}
