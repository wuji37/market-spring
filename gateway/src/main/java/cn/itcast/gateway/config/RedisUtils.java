package cn.itcast.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component //项目运行时就注入Spring容器,用于过滤器先于redisTemplate的注入，所以需要创建一个包含静态redisTemplate的对象，在过滤之前就将注入
public class RedisUtils {

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate<String,String> redis;


    @Autowired
    private Environment environment_auto;
    //赋值一个静态的redisTemplate
    public static RedisTemplate redisTemplate;

    public static Environment environment;

    @PostConstruct //此注解表示构造时赋值
    public void redisTemplate() {
        redisTemplate = this.redis;
    }

    @PostConstruct
    public void setEnvironment(){
        environment=environment_auto;

    }
}
