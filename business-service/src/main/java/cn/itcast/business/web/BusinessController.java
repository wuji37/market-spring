package cn.itcast.business.web;

import cn.itcast.business.mapper.BusinessMapper;
import cn.itcast.business.pojo.AllUser;
import cn.itcast.business.pojo.Business;
import cn.itcast.business.service.BusinessService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Blob;
import java.util.List;

@RestController
@RequestMapping("business/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @RequestMapping("all")
    public List<Business> getAllBusiness(){
        return businessService.getAllBusiness();
    }

    @RequestMapping("id/{id}")
    public Business getBusinessById(@PathVariable("id") int id){
        return businessService.getBusinessById(id);
    }

    @RequestMapping("userId/{id}")
    public Business getBusinessByUserId(@PathVariable("id") int id){
        return businessService.getBusinessByUserId(id);
    }

    @RequestMapping("update")
    public void updateBusiness(@RequestBody Business business){
        businessService.updateBusiness(business);
    }

    @RequestMapping("insert")
    public void insertBusiness(@RequestBody Business business){
        businessService.insertBusiness(business);
    }

    @RequestMapping("delete/id/{id}")
    public void deleteBusinessById(@PathVariable("id")int id){
        businessService.deleteBusinessById(id);
    }

    @RequestMapping("delete/userId/{id}")
    public void deleteBusinessByUserId(@PathVariable("id") int userId){
        businessService.deleteBusinessByUserId(userId);
    }
}
