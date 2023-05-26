package cn.itcast.user.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EncryptionConfig {

    @Autowired
    private Environment environment_auto;

    public static Environment environment;

    public static String key;

    @PostConstruct
    private void initEnvironment(){
        environment=environment_auto;
        key=environment.getProperty("encryption.key");
    }

}
