package cn.itcast.business.mapper;

import cn.itcast.business.pojo.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Blob;
import java.util.List;


public interface GoodsMapper {

    @Select("select * from goods")
    List<Goods> getAllGoods();

    @Select("select * from goods where id=#{id}")
    Goods getGoodsById(int id);

    @Select("select * from goods where business_id=#{id}")
    List<Goods> getGoodsByBusiness_id(int id);

    @Insert("insert into goods(id,business_id,photo,name,introduction,price,discount,sale,score) values(null,#{business_id},#{photo},#{name},#{introduction},#{price},#{discount},#{sale},#{score})")
    void insertGoods(int business_id, Blob photo,String name,String introduction,float  price,float discount,int sale,float score);

    @Update("update goods set business_id=#{business_id},photo=#{photo},name=#{name},introduction=#{introduction},price=#{price},discount=#{discount},sale=#{sale},score=#{score}")
    void updateGoods(int business_id, Blob photo,String name,String introduction,float  price,float discount,int sale,float score);

    @Delete("delete from goods where id=#{id}")
    void deleteGoodsById(int id);

    @Delete("delete from goods where business_id=#{id}")
    void deleteGoodsByBusinessId(int id);
}
