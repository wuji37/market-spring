package cn.itcast.business.feign.clients;

import cn.itcast.business.pojo.Business;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("businessService")
public interface BusinessClient {

    @GetMapping("/business/id/{id}")
    Business findBusinessById(@PathVariable("id") int id);
}
