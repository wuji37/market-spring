package cn.itcast.business.mapper;


import cn.itcast.business.pojo.Business_all;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Blob;
import java.util.List;

public interface Business_allMapper {

    @Select("select * from business_all ")
    List<Business_all> getAllBusiness();

    @Select("select * from business_all where id=#{id}")
    Business_all getBusinessById(int id);

    @Select("select * from business_all where userId=#{id}")
    List<Business_all> getBusinessByUserId(int id);

    @Select("select * from business_all where businessUserId=#{id}")
    List<Business_all> getBusinessByBusinessUserId(int id);

    @Select("select * from business_all where businessId=#{id}")
    List<Business_all> getBusinessByBusinesssId(int id);

    @Update("update business_all set userId=#{userId},businessId=#{businessId},businessUserId=#{businessUserId} where id=#{id}")
    void updateBusiness_all(int userId,int businessId,int businessUserId,int id);

    @Insert("insert into Business_all(id,userId,businessId,businessUserId) values(null,#{userId},#{businessId},#{businessUserId})")
    void insertBusiness(int userId,int businessId,int businessUserId);

    @Delete("delete from Business_all where id=#{id}")
    void deleteBusinessById(int id);

    @Delete("delete from Business_all where businessUserId=#{userId}")
    void deleteBusinessByBusinessUserId(int userId);

    @Delete("delete from Business_all where businessId=#{userId}")
    void deleteBusinessByBusinessId(int userId);

    @Delete("delete from Business_all where userId=#{userId}")
    void deleteBusinessByUserId(int userId);

}
