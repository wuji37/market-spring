package cn.itcast.business.mapper;

import cn.itcast.business.pojo.AllUser;
import cn.itcast.business.pojo.Business;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Blob;
import java.util.List;

public interface BusinessMapper {
    @Select("select * from business")
    List<Business> getAllBusiness();

    @Select("select * from business where id=#{id}")
    Business getBusinessById(int id);

    @Select("select * from business where businessUserId=#{id}")
    Business getBusinessByUserId(int id);

    @Update("update business set businessUserId=#{userId},name=#{name},introduction=#{introduction},photo=#{photo}")
    void updateBusiness(int userId, String name, String introduction, Blob photo);

    @Insert("insert into Business(id,businessUserId,name,introduction,score,photo) values(null,#{userId},#{name},#{intro},#{score},#{photo})")
    void insertBusiness(int userId,String name,String intro,float score,Blob photo);

    @Delete("delete from Business where id=#{id}")
    void deleteBusinessById(int id);

    @Delete("delete from Business where businessUserId=#{userId}")
    void deleteBusinessByUserId(int userId);
}
