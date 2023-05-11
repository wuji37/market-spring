package cn.itcast.business.web;

import cn.itcast.business.mapper.Business_allMapper;
import cn.itcast.business.pojo.Business_all;
import cn.itcast.business.service.Business_allService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("businessAll")
public class Business_allController {

    @Autowired
    private Business_allService business_allService;

    @RequestMapping("all")
    public List<Business_all> getAll(){
        System.err.println(business_allService.getAllBusiness());
        return business_allService.getAllBusiness();
    }

    @RequestMapping("id/{id}")
    public Business_all getById(@PathVariable("id") int id){
        return business_allService.getBusinessById(id);
    }


    @RequestMapping("businessId/{id}")
    public List<Business_all> getByBusinessId(@PathVariable("id") int id){
        return business_allService.getBusinessByBusinessId(id);
    }

    @RequestMapping("userId/{id}")
    public List<Business_all> getBusinessByUserId(@PathVariable("id") int id){
        return business_allService.getBusinessByUserId(id);
    }

    @RequestMapping("businessUserId/{id}")
    public List<Business_all> getByBusinessUserId(@PathVariable("id") int id){
        return business_allService.getBusinessByBusinessUserId(id);
    }

    @RequestMapping("update")
    public void update(@RequestBody Business_all business){
        business_allService.updateBusiness_all(business);
    }

    @RequestMapping("insert")
    public void insert(@RequestBody Business_all business){
        business_allService.insertBusiness(business);
    }

    @RequestMapping("delete/id/{id}")
    public void deleteById(@PathVariable("id") int id){
        business_allService.deleteBusinessById(id);
    }

    @RequestMapping("delete/userId/{id}")
    public void deleteByUserId(@PathVariable("id") int id){
        business_allService.deleteBusinessByUserId(id);
    }

    @RequestMapping("delete/businessId/{id}")
    public void deleteByBusinessId(@PathVariable("id") int id){
        business_allService.deleteBusinessByBusinessId(id);
    }

    @RequestMapping("delete/businessUserId/{id}")
    public void deleteByBusinessUserId(@PathVariable("id") int id){
        business_allService.deleteBusinessByBusinessUserId(id);
    }

}
