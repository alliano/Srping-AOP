package sprig.aop.aop;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j @Component
public class ApplicationWithAround {
    
    public void execute(String name) {
        log.info("my name "+ name);
    }
}
