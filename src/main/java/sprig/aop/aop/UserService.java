package sprig.aop.aop;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import sprig.aop.aop.helper.UserRequest;

@Component @Slf4j
public class UserService {

    public void saveUser(UserRequest userRequest) {
        log.info("SAVING NEW USER");
    }
}
