package cn.itcast.gateway.IPGatewayFilter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;


@Component
public class IPBlackListFilterFactory  implements GatewayFilterFactory<IPBlackListFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new IPBlackListFilter();
    }

    @Override
    public String name() {
        return "IPBlackListFilterFactory";
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config {
    }
}