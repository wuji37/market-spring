package cn.itcast.user.mapper;

import cn.itcast.user.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    List<User> getAllUser();

    @Select("select * from user where id=#{id}")
    User getUserById(int id);

    @Insert("insert into user(id,name,phone,age,sex,address,photo) values(null,#{name},#{phone},#{age},#{sex},#{address},null)")
    void insertUser(String name,int phone,String sex,int age,String address);

    @Delete("delete from user where id=#{id}")
    void deleteUserById(int id);

    @Update("update user set name=#{name},age=#{age},sex=#{sex},phone=#{phone},address=#{address} where id=#{id}")
    void updateUserById(String name,int phone,String sex,int age,String address,int id);

}
