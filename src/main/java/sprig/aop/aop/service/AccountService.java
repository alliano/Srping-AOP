package sprig.aop.aop.service;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component @Slf4j
public class AccountService {
    
    public void addAccount() {
        log.info("add acound service.....");
    }
}
