package cn.itcast.gateway.Flow;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class LeakBucketFilterFactory implements GatewayFilterFactory<LeakBucketFilterFactory.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        return new LeakBucketFilter();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config {
    }

    @Override
    public String name() {
        return "LeakBucketFilterFactory";
    }
}
