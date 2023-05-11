package cn.itcast.business.mapper;

import cn.itcast.business.pojo.Business;
import cn.itcast.business.pojo.Goods;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import javax.persistence.Lob;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;


public interface GoodsMapper {

    @Select("select * from goods")
    //@Select("select * FROM goods,business where goods.businessId=business.id")
    List<Goods> getAllGoods();
//    @Select("select photo from goods where id=#{id}")
//    @Results({@Result(property = "image", column = "photo", jdbcType = JdbcType.BLOB, javaType = byte[].class)})
//    byte[] getImageById(int id);

    @Select("select photo from goods where id=#{id}")
    @Results({@Result(property = "image", column = "photo", jdbcType = JdbcType.VARCHAR, javaType = String.class)})
    String getImageById(int id);

    @Select("select business.id,business.businessUserId,business.`name`,business.introduction,business.score,business.photo FROM (select * FROM goods where id={id})as goods JOIN business on goods.businessId=business.id;")
    Business getBusinessByGoodsId(int id);

    @Select("select * from goods where id=#{id}")
    Goods getGoodsById(int id);

    @Select("select * from goods where businessId=#{id}")
    List<Goods> getGoodsByBusiness_id(int id);

    @Insert("insert into goods(id,businessId,photo,name,introduction,price,discount,sale,score) values(null,#{businessId},#{photo},#{name},#{introduction},#{price},#{discount},#{sale},#{score})")
    void insertGoods(int businessId, String photo,String name,String introduction,float  price,float discount,int sale,float score);

    @Update("update goods set businessId=#{businessId},photo=#{photo},name=#{name},introduction=#{introduction},price=#{price},discount=#{discount},sale=#{sale},score=#{score} where id=#{id}")
    void updateGoods(int businessId, String photo,String name,String introduction,float  price,float discount,int sale,float score,int id);

    @Delete("delete from goods where id=#{id}")
    void deleteGoodsById(int id);

    @Delete("delete from goods where businessId=#{id}")
    void deleteGoodsByBusinessId(int id);
}
