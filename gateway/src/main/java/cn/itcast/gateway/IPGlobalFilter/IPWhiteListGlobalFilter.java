package cn.itcast.gateway.IPGlobalFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

//@Order(-1)
//@Component
public class IPWhiteListGlobalFilter implements GlobalFilter {
    @Autowired
    private Environment environment;
    private  List<String> WHITELIST_IPS;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        WHITELIST_IPS= Arrays.asList(environment.getProperty("IP.WhiteList").split(","));
        System.out.println("IP白名单列表:"+WHITELIST_IPS);
        String clientIP = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        System.out.println("当前IP："+clientIP);

        if (!WHITELIST_IPS.contains(clientIP)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
