package sprig.aop.aop;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class Greeting {
    
    public void say(String greeting, String name) {
        log.info("greting....");
    }
}
