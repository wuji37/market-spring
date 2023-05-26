package cn.itcast.gateway.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

//@Order(-5)
//@Component
public class JWTGlobalFilter implements GlobalFilter {

    @Autowired
    private Environment environment;
    private String userId;
    private String username;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        userId=environment.getProperty("Jwt.args.userId");
        username=environment.getProperty("Jwt.args.username");

        System.out.println("构造方法："+userId+" "+username);

        String jwt=exchange.getRequest().getHeaders().getFirst("Authorization");

        String[] s=splitString(jwt);

        if(!isValidJwt(s)){
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }


        return chain.filter(exchange);
    }


    private boolean isValidJwt(String[] s) {

        Long iatTimeStamp= Long.valueOf(s[0]);
        Instant instant=Instant.ofEpochSecond(iatTimeStamp);
        LocalDateTime iatDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime currentDateTime = LocalDateTime.now(); // 获取当前时间
        Instant currentTimeStamp=Instant.now();
        Duration duration = Duration.between(iatDateTime, currentDateTime); // 计算时间差
        System.out.println("iatDateTime:"+iatDateTime);
        System.out.println("currentDateTime:"+currentDateTime);

        System.out.println("current Instant:"+currentTimeStamp.toEpochMilli()/1000);   //返回以秒为单位的时间戳，需要修改中间的部分
        // {"sub":"12345","name":"John Doe","iat":1684738394}  ，将一下代码中的iat替换为当前以秒为单位的时间戳，进行解码，然后修改postman中请求头数据

        if(duration.toHours()<550 && s[1].equals(userId) && s[2].equals(username))
            return true;
        else
            return false;
    }

    private String[] splitString(String jwt){
        String[] s=new String[3];
        String[] jwtParts = jwt.split("\\.");
        String encodedPayload = jwtParts[1];

        // Base64 decode the payload
        byte[] decodedPayload = Base64.getDecoder().decode(encodedPayload);

        try {
            // Convert the decoded payload to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = new String(decodedPayload);

            // Extract the desired data from the JSON payload
            String userId = objectMapper.readTree(jsonPayload).get("sub").asText();
            String username = objectMapper.readTree(jsonPayload).get("name").asText();
            String iat = objectMapper.readTree(jsonPayload).get("iat").asText();


            s[0]=iat;
            s[1]=userId;
            s[2]=username;
            return  s;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
