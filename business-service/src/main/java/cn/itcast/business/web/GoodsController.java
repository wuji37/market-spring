package cn.itcast.business.web;


import cn.itcast.business.pojo.Goods;
import cn.itcast.business.service.GoodsService;
import cn.itcast.feign.clients.user.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;
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


    @RequestMapping("image")
    public String getImage() throws IOException {
        String imagePath = "C:\\Users\\ASUS\\Desktop\\1.jpg"; // 本地图片的路径
        File file = new File(imagePath);
        byte[] imageBytes = Files.readAllBytes(file.toPath());
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return base64Image;
    }

    @RequestMapping("image/{id}")
    public String getImageById(@PathVariable("id") int id){

        return goodsService.getImageById(id);
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
