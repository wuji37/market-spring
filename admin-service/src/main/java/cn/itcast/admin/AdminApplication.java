package cn.itcast.admin;

import cn.itcast.feign.clients.business.BusinessClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("cn.itcast.admin.mapper")
@EnableFeignClients(clients ={BusinessClient.class})
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args){
        SpringApplication.run(AdminApplication.class,args);
    }
}
