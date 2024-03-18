package Rajas.com.botRest.BotRest.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public  class BotDetails{
    @Value("${bot.id}")
    private String id;

    @Bean
    public String getUserBucketPath() {
        return id;
       }
}

