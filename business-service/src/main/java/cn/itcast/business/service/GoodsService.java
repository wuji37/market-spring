package cn.itcast.business.service;


import cn.itcast.business.mapper.GoodsMapper;
import cn.itcast.business.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getAllGoods(){
        return goodsMapper.getAllGoods();
    }



    public Goods getGoodsById(int id){
        return goodsMapper.getGoodsById(id);
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
