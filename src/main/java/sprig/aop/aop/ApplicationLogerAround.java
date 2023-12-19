package sprig.aop.aop;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component @Slf4j
public class ApplicationLogerAround {
    
    public void execute(String name) {
        log.info("my name "+name);
    }
}
