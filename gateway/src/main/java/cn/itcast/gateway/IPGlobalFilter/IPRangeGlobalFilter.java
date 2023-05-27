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

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

//@Order(-3)
//@Component
public class IPRangeGlobalFilter implements GlobalFilter {

    @Autowired
    private Environment environment;
    private String ipStart;
    private String ipEnd;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ipStart=environment.getProperty("IP.Range.ipStart");
        ipEnd=environment.getProperty("IP.Range.ipEnd");

        String clientIP = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        // 检查客户端 IP 是否在指定的 IP 地址段范围内
        if (isWithinIPRange(clientIP)) {
            return chain.filter(exchange);
        } else {
            // IP 不在范围内，返回拒绝访问的响应
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
    }

    private boolean isWithinIPRange(String ip) {
        try {
            InetAddress start = InetAddress.getByName(ipStart);
            InetAddress end = InetAddress.getByName(ipEnd);
            InetAddress target = InetAddress.getByName(ip);

            System.out.println("start:"+start);
            System.out.println("end:"+end);
            System.out.println("target:"+target);
            return isInRange(target, start, end);
        } catch (UnknownHostException e) {
            // 处理异常情况，例如无效的 IP 地址
            return false;
        }
    }

    private boolean isInRange(InetAddress target, InetAddress start, InetAddress end) {
        BigInteger targetInt = new BigInteger(target.getAddress());
        BigInteger startInt = new BigInteger(start.getAddress());
        BigInteger endInt = new BigInteger(end.getAddress());

        return targetInt.compareTo(startInt) >= 0 && targetInt.compareTo(endInt) <= 0;
    }

}
