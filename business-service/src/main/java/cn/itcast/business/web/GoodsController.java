package cn.itcast.business.web;


import cn.itcast.business.pojo.Goods;
import cn.itcast.business.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("all")
    public List<Goods> getAllGoods(){
        return goodsService.getAllGoods();
    }

    @RequestMapping("id/{id}")
    public Goods getGoodsById(@PathVariable("id") int id){
        return goodsService.getGoodsById(id);
    }

    @RequestMapping("businessId/{id}")
    public List<Goods> getGoodsByBusinessId(@PathVariable("id") int id){
        return goodsService.getGoodsByBusiness_id(id);
    }

    @RequestMapping("insert")
    public void insetGoods(@RequestBody Goods goods){
        goodsService.insertGoods(goods);
    }

    @RequestMapping("update")
    public void updateGoods(@RequestBody Goods goods){
        goodsService.updateGoods(goods);
    }

    @RequestMapping("delete/id/{id}")
    public void deleteGoodsById(@PathVariable("id") int id){
        goodsService.deleteGoodsById(id);
    }

    @RequestMapping("delete/businessId/{id}")
    public void deleteGoodsByBusinessId(@PathVariable("id") int id){
        goodsService.deleteGoodsByBusinessId(id);
    }

}
