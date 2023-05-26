package cn.itcast.gateway.Flow;


import cn.itcast.gateway.config.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Order(2)
@Component
public class RedisFilter implements GatewayFilter {

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String clientIP = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        String key = "rate_limit:" + clientIP;

//        System.out.println("key"+key);
//        System.out.println(redisUtils.redisTemplate);

        // 获取客户端请求计数
        Long count = redisUtils.redisTemplate.opsForValue().increment(key, 1);

//        System.out.println("key:"+key);
//        System.out.println(redisUtils.redisTemplate.opsForValue().get(key));

        // 如果计数超过阈值，则拒绝请求
        if (count != null && count > 5) {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }

        // 执行请求处理
        return chain.filter(exchange);
    }



}
