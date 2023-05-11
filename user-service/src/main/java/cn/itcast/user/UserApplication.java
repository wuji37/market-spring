package cn.itcast.user;

import cn.itcast.feign.clients.business.BusinessClient;
import cn.itcast.user.pojo.AllUser;
import cn.itcast.user.web.AllUserController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("cn.itcast.user.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {BusinessClient.class})
public class UserApplication {
    public static void main(String[] args){
        SpringApplication.run(UserApplication.class,args);
    }
}
