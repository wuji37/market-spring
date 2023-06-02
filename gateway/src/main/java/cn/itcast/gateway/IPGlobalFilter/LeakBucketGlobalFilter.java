package cn.itcast.gateway.IPGlobalFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

//@Order(3)
//@Component
public class LeakBucketGlobalFilter implements GlobalFilter {
    @Autowired
    private Environment environment;
    private final Queue<Instant> bucket;
    public LeakBucketGlobalFilter() {
        this.bucket = new LinkedList<>();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        int capacity = Integer.parseInt(environment.getProperty("LeakBucketGlobalFilter.args.capacity"));
        int leakRate = Integer.parseInt(environment.getProperty("LeakBucketGlobalFilter.args.leakRate"));

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
