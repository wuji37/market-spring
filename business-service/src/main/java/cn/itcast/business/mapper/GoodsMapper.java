package cn.itcast.business.mapper;

import cn.itcast.business.pojo.Business;
import cn.itcast.business.pojo.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Blob;
import java.util.List;


public interface GoodsMapper {

    @Select("select * from goods")
    //@Select("select * FROM goods,business where goods.businessId=business.id")
    List<Goods> getAllGoods();

    @Select("select business.id,business.businessUserId,business.`name`,business.introduction,business.score,business.photo FROM (select * FROM goods where id={id})as goods JOIN business on goods.businessId=business.id;")
    Business getBusinessByGoodsId(int id);

    @Select("select * from goods where id=#{id}")
    Goods getGoodsById(int id);

    @Select("select * from goods where businessId=#{id}")
    List<Goods> getGoodsByBusiness_id(int id);

    @Insert("insert into goods(id,businessId,photo,name,introduction,price,discount,sale,score) values(null,#{businessId},#{photo},#{name},#{introduction},#{price},#{discount},#{sale},#{score})")
    void insertGoods(int businessId, Blob photo,String name,String introduction,float  price,float discount,int sale,float score);

    @Update("update goods set businessId=#{businessId},photo=#{photo},name=#{name},introduction=#{introduction},price=#{price},discount=#{discount},sale=#{sale},score=#{score} where id=#{id}")
    void updateGoods(int businessId, Blob photo,String name,String introduction,float  price,float discount,int sale,float score,int id);

    @Delete("delete from goods where id=#{id}")
    void deleteGoodsById(int id);

    @Delete("delete from goods where businessId=#{id}")
    void deleteGoodsByBusinessId(int id);
}
