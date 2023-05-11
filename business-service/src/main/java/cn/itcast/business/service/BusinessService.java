package cn.itcast.business.service;

import cn.itcast.business.mapper.BusinessMapper;
import cn.itcast.business.pojo.Business;
import cn.itcast.business.pojo.Business_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private Business_userService businessUserService;

    public List<Business> getAllBusiness(){
        return businessMapper.getAllBusiness();
    }

    public Business getBusinessById(int id){
        Business business=businessMapper.getBusinessById(id);
        Business_user businessUser=businessUserService.getById(business.getBusinessUserId());
        business.setBusinessUser(businessUser);
        return business;
    }

    public Business getBusinessByUserId(int id){
        return businessMapper.getBusinessByUserId(id);
    }


    public void updateBusiness(Business business){
        businessMapper.updateBusiness(business.getBusinessUserId(),business.getName(),business.getIntroduction(),business.getPhoto());
    }

    public void insertBusiness(Business business){
        businessMapper.insertBusiness(business.getBusinessUserId(),business.getName(),business.getIntroduction(),business.getScore(),business.getPhoto());
    }

    public void deleteBusinessById(int id){
        businessMapper.deleteBusinessById(id);
    }

    public void deleteBusinessByUserId(int userId){
        businessMapper.deleteBusinessByUserId(userId);
    }
}
