package sprig.aop.aop;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component @Slf4j
public class ApplicationService {
    
    public void hello() {
        log.info("Hello from ApplicationService");
    }

    public void hello2() {
        log.info("Hello 2 from ApplicationService");
    }
}
