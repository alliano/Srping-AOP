package sprig.aop.aop.service;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component @Slf4j
public class AuthenticationService {

    public void authenticate(String username, String password) {
        log.info("authenticate.......");
    }
}
