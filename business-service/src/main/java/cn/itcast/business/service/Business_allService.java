package cn.itcast.business.service;

import cn.itcast.business.mapper.BusinessMapper;
import cn.itcast.business.mapper.Business_allMapper;
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
public class Business_allService {

    @Autowired
    private Business_allMapper business_allMapper;

    public List<Business_all> getAllBusiness(){
        return business_allMapper.getAllBusiness();
    }

    public Business_all getBusinessById(int id){
        return business_allMapper.getBusinessById(id);
    }

    public List<Business_all> getBusinessByUserId(int id){
        return business_allMapper.getBusinessByUserId(id);
    }

    public void updateBusiness_all(Business_all business_all){
        //nt userId,int business_id,int business_user_id
        business_allMapper.updateBusiness_all(business_all.getUser_id(),business_all.getBusiness_id(), business_all.getBusiness_user_id());
    }


    public void insertBusiness(Business_all business_all){
        business_allMapper.insertBusiness(business_all.getUser_id(),business_all.getBusiness_id(), business_all.getBusiness_user_id());
    }


    public void deleteBusinessById(int id){
        business_allMapper.deleteBusinessById(id);
    }


    public void deleteBusinessByBusinessUserId(int userId){
        business_allMapper.deleteBusinessByBusinessUserId(userId);
    }

    public void deleteBusinessByBusinessId(int userId){
        business_allMapper.deleteBusinessByBusinessId(userId);
    }

    public void deleteBusinessByUserId(int userId){
        business_allMapper.deleteBusinessByUserId(userId);
    }
}
