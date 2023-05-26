package cn.itcast.gateway.Flow;


import cn.itcast.gateway.config.RedisUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

@Order(4)
@Component
public class LeakBucketFilter implements GatewayFilter {


    @Autowired
    private RedisUtils redisUtils;


    private final Queue<Instant> bucket;

    //在全局过滤器中无法使用@Value来获取值，需要使用environment来获取配置文件中的值
    public LeakBucketFilter() {
        this.bucket = new LinkedList<>();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println(redisUtils.environment);

        int capacity = Integer.parseInt(redisUtils.environment.getProperty("LeakBucketGlobalFilter.args.capacity"));
        int leakRate = Integer.parseInt(redisUtils.environment.getProperty("LeakBucketGlobalFilter.args.leakRate"));

//        System.out.println(capacity+" "+leakRate);
        // 处理请求
        bucket.offer(Instant.now()); // 请求放入漏桶

        // 漏出速率控制
        Instant currentTime = Instant.now();
        while (bucket.peek() != null && Duration.between(bucket.peek(), currentTime).getSeconds() > leakRate) {
            bucket.poll(); // 从漏桶中移除过期的请求
        }

        if (bucket.size() >= capacity) {
            // 漏桶已满，拒绝请求或进行相应处理
            //return Mono.error(new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS));
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
