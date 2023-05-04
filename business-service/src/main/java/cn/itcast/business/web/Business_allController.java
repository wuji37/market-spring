package cn.itcast.business.web;

import cn.itcast.business.mapper.Business_allMapper;
import cn.itcast.business.pojo.Business_all;
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
    private Business_allMapper business_allMapper;

    @RequestMapping("all")
    public List<Business_all> getAll(){
        System.err.println(business_allMapper.getAllBusiness());
        return business_allMapper.getAllBusiness();
    }

    @RequestMapping("id/{id}")
    public Business_all getById(@PathVariable("id") int id){
        return business_allMapper.getBusinessById(id);
    }


    @RequestMapping("businessId/{id}")
    public List<Business_all> getByBusinessId(@PathVariable("id") int id){
        return business_allMapper.getBusinessByBusinesssId(id);
    }

    @RequestMapping("businessUserId/{id}")
    public List<Business_all> getByBusinessUserId(@PathVariable("id") int id){
        return business_allMapper.getBusinessByBusinessUserId(id);
    }

    @RequestMapping("update")
    public void update(@RequestBody Business_all business){
        business_allMapper.updateBusiness_all(business.getUser_id(), business.getBusiness_id(), business.getBusiness_id());
    }

    @RequestMapping("insert")
    public void insert(@RequestBody Business_all business){
        business_allMapper.insertBusiness(business.getId(), business.getBusiness_id(), business.getBusiness_user_id());
    }

    @RequestMapping("delete/id/{id}")
    public void deleteById(@PathVariable("id") int id){
        business_allMapper.deleteBusinessById(id);
    }

    @RequestMapping("delete/userId/{id}")
    public void deleteByUserId(@PathVariable("id") int id){
        business_allMapper.deleteBusinessByUserId(id);
    }

    @RequestMapping("delete/businessId/{id}")
    public void deleteByBusinessId(@PathVariable("id") int id){
        business_allMapper.deleteBusinessByBusinessId(id);
    }

    @RequestMapping("delete/businessUserId/{id}")
    public void deleteByBusinessUserId(@PathVariable("id") int id){
        business_allMapper.deleteBusinessByBusinessUserId(id);
    }

}
