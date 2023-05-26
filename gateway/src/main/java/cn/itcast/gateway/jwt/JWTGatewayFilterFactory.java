package cn.itcast.gateway.jwt;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Order(-5)
@Component
public class JWTGatewayFilterFactory implements GatewayFilterFactory<JWTGatewayFilterFactory.Config> {
    @Override
    public String name() {
        return "JWTGatewayFilterFactory";
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new JWTGatewayFilter();
    }

    public static class Config{

    }
}
