package cn.itcast.gateway.IPGatewayFilter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class IPWhiteListFilterFactory implements GatewayFilterFactory<IPWhiteListFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new IPWhiteListFilter();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config {
    }
}
