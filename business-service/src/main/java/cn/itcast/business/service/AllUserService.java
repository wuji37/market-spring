package cn.itcast.business.service;

import cn.itcast.business.mapper.AllUserMapper;
import cn.itcast.business.pojo.AllUser;
import cn.itcast.business.pojo.Business_all;
import cn.itcast.business.pojo.Business_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllUserService {

    @Autowired
    private AllUserMapper allUserMapper;
    @Autowired
    private Business_userService businessUserService;

    @Autowired
    private Business_allService businessAllService;

    public List<AllUser> getAllUser(){
        return allUserMapper.getAllUser();
    }

    public AllUser getUserById(int id){
        AllUser allUser=allUserMapper.getUserById(id);
        List<Business_all> businessAllList=businessAllService.getBusinessByUserId(allUser.getId());
        Business_user  businessUser=businessUserService.getById(businessAllList.get(0).getUserId());
        allUser.setBusinessUser(businessUser);
        return allUser;
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
