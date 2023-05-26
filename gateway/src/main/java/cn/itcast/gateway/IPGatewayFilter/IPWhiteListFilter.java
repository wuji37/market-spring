package cn.itcast.gateway.IPGatewayFilter;

import cn.itcast.gateway.config.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import java.util.Arrays;
import java.util.List;

@Component
public class IPWhiteListFilter implements GatewayFilter {
    @Autowired
    private RedisUtils redisUtils;
    private List<String> WHITELIST_IPS;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        WHITELIST_IPS= Arrays.asList(redisUtils.environment.getProperty("IP.WhiteList").split(","));
        String clientIP = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        System.out.println("IP白名单列表："+WHITELIST_IPS);
        System.out.println("当前IP"+clientIP);
        if (!WHITELIST_IPS.contains(clientIP)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }


        return chain.filter(exchange);
    }
}
