package sprig.aop.aop.service;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component @Slf4j
public class PaymentService {
    
    public void pay() {
        log.info("Transaction ......");
    }
}
