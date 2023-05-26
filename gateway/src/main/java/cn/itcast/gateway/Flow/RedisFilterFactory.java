package cn.itcast.gateway.Flow;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisFilterFactory implements GatewayFilterFactory<RedisFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new RedisFilter();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config {
    }
}