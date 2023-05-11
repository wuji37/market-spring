package cn.itcast.business.service;


import cn.itcast.business.feign.clients.BusinessClient;
import cn.itcast.business.mapper.GoodsMapper;
import cn.itcast.business.pojo.Business;
import cn.itcast.business.pojo.Goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BusinessClient businessClient;

    public List<Goods> getAllGoods(){
        return goodsMapper.getAllGoods();
    }

    public Goods getGoodsById(int id){
//        Goods goods=goodsMapper.getGoodsById(id);
//        String url="http://businessService/id/"+goods.getBusinessId();
//        Business business=restTemplate.getForObject(url,Business.class);
//        goods.setBusiness(business);
//        return goods;

         Goods goods=goodsMapper.getGoodsById(id);
         Business business=businessClient.findBusinessById(goods.getBusinessId());
         goods.setBusiness(business);
         return goods;
    }

    public String getImageById(int id){
        return goodsMapper.getImageById(id);
    }

    public List<Goods> getGoodsByBusiness_id(int id){
        return goodsMapper.getGoodsByBusiness_id(id);
    }

    public void insertGoods(Goods goods){
        goodsMapper.insertGoods(goods.getBusinessId(),goods.getPhoto(),goods.getName(),goods.getIntroduction(),goods.getPrice(),goods.getDiscount(),goods.getSale(),goods.getScore());
    }

    public void updateGoods(Goods goods){
        goodsMapper.updateGoods(goods.getBusinessId(),goods.getPhoto(),goods.getName(),goods.getIntroduction(),goods.getPrice(),goods.getDiscount(),goods.getSale(),goods.getScore(), goods.getId());
    }

    public void deleteGoodsById(int id){
        goodsMapper.deleteGoodsById(id);
    }

    public void deleteGoodsByBusinessId(int id){
        goodsMapper.deleteGoodsByBusinessId(id);
    }

}
