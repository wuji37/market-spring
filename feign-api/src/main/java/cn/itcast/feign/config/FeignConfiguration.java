package cn.itcast.feign.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class FeignConfiguration {
//
//    @Bean
//    public CachingSpringLoadBalancerFactory cachingFactory(SpringClientFactory clientFactory) {
//        return new CachingSpringLoadBalancerFactory(clientFactory);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
//                              SpringClientFactory clientFactory) throws NoSuchAlgorithmException, KeyManagementException {
//        SSLContext ctx = SSLContext.getInstance("SSL");
//        X509TrustManager tm = new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] chain, String authType) {
//            }
//            @Override
//            public void checkServerTrusted(X509Certificate[] chain, String authType) {
//            }
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                //如果这里后续报空指针，就return new X509Certificate[0]
//                return null;
//            }
//        };
//        ctx.init(null, new TrustManager[]{tm}, null);
//        return new LoadBalancerFeignClient(new Client.Default(ctx.getSocketFactory(),
//                (hostname, session) -> true),
//                cachingFactory, clientFactory);
//    }
//}

//@Configuration
//public class FeignConfiguration {
//
//    @Autowired
//    private ObjectFactory<HttpMessageConverters> messageConverters;
//
//    @Bean
//    public Client feignClient() throws NoSuchAlgorithmException, KeyManagementException {
//        SSLContext sslContext = SSLContext.getInstance("SSL");
//        sslContext.init(null, getTrustManagers(), null);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLContext(sslContext)
//                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//                .build();
//
//        return new ApacheHttpClient(httpClient);
//    }
//
//    private TrustManager[] getTrustManagers() {
//        return new TrustManager[]{
//                new X509TrustManager() {
//                    @Override
//                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
//                    }
//
//                    @Override
//                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
//                    }
//
//                    @Override
//                    public X509Certificate[] getAcceptedIssuers() {
//                        return null;
//                    }
//                }
//        };
//    }
//
//    @Bean
//    public feign.codec.Encoder feignEncoder() {
//        return new SpringEncoder(messageConverters);
//    }
//
//    @Bean
//    public feign.codec.Decoder feignDecoder() {
//        return new SpringDecoder(messageConverters);
//    }
//
//
//    public void readP12(){
//
//    }
//}
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

@Configuration
public class FeignConfiguration {

    @Bean
    public SSLContext sslContext() throws Exception {
        // 加载证书和私钥
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream keyStoreStream = new ClassPathResource("server.p12").getInputStream()) {
            keyStore.load(keyStoreStream, "123456".toCharArray());
        }

        // 初始化SSL上下文
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, "123456".toCharArray());

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
        return sslContext;
    }
}
