package cn.itcast.gateway.IPGatewayFilter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class IPRangeFilterFactory  implements GatewayFilterFactory<IPRangeFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new IPRangeGatewayFilter();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config {
        private String redisHost="127.0.0.1";
        private int redisPort=6379;
    }
}