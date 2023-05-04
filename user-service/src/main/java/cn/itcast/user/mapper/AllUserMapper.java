package cn.itcast.user.mapper;

import cn.itcast.user.pojo.AllUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AllUserMapper {
    @Select("select * from alluser")
    List<AllUser> getAllUser();

    @Select("select * from alluser where id=#{id}")
    AllUser getUserById(int id);

    @Select("select * from alluser where username=#{username}")
    AllUser getUserByUsername(String username);

    @Update("update alluser set password=#{newPassword} where username=#{username}")
    void updateUserPasswordByUsername(String username,String newPassword);

    @Insert("insert into alluser(id,username,password) values(null,#{username},#{password})")
    void insertUser(String password,String username);

    @Delete("delete from alluser where id=#{id}")
    void deleteUserById(int id);

    @Delete("delete from alluser where username=#{username}")
    void deleteUserByUsername(String username);
}
