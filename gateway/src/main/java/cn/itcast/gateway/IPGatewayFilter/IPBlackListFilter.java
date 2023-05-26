package cn.itcast.gateway.IPGatewayFilter;

import cn.itcast.gateway.config.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class IPBlackListFilter implements GatewayFilter {

    @Autowired
    private RedisUtils redisUtils;
    private List<String> WHITELIST_IPS;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        WHITELIST_IPS=Arrays.asList(redisUtils.environment.getProperty("IP.blackList").split(","));
        String clientIP = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        System.out.println("黑名单IP列表："+WHITELIST_IPS);
        System.out.println("当前IP:"+clientIP);
        System.out.println();

        if (WHITELIST_IPS.contains(clientIP)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }


        return chain.filter(exchange);
    }
}