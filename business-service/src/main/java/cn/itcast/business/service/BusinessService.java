package cn.itcast.business.service;

import cn.itcast.business.mapper.BusinessMapper;
import cn.itcast.business.pojo.AllUser;
import cn.itcast.business.pojo.Business;
import cn.itcast.business.pojo.Business_all;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    public List<Business> getAllBusiness(){
        return businessMapper.getAllBusiness();
    }

    public Business getBusinessById(int id){
        return businessMapper.getBusinessById(id);
    }

    public AllUser getBusinessByUserId(int id){
        return businessMapper.getBusinessByUserId(id);
    }


    public void updateBusiness(Business business){
        businessMapper.updateBusiness(business.getBusiness_user_id(),business.getName(),business.getIntroduction(),business.getPhoto());
    }

    public void insertUser(Business business){
        //int userId,String name,String intro,float score,Blob photo
        businessMapper.insertBusiness(business.getBusiness_user_id(),business.getName(),business.getIntroduction(),business.getScore(),business.getPhoto());
    }

    public void deleteBusinessById(int id){
        businessMapper.deleteBusinessById(id);
    }

    public void deleteBusinessByUsername(int userId){
        businessMapper.deleteBusinessByUserId(userId);
    }
}
