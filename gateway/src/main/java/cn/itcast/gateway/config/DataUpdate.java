package cn.itcast.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class DataUpdate {

    @Autowired
    private RedisUtils  redisUtils;
    private String match="rate_limit:";

//    @Scheduled(fixedRate = 10000) // 每10秒执行一次
    public void updateData() throws IOException {
        ScanOptions options=ScanOptions.scanOptions().match(match+"*").build();

        RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisUtils.redisTemplate.getKeySerializer();
        Cursor cursor = (Cursor) redisUtils.redisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));

        while(cursor.hasNext()){
            redisUtils.redisTemplate.opsForValue().set(cursor.next().toString(),"0");
        }
        //切记这里一定要关闭，否则会耗尽连接数
        cursor.close();

    }

}
