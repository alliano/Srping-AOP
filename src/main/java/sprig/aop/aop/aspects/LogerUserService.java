package sprig.aop.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import sprig.aop.aop.helper.UserRequest;

@Component @Slf4j @Aspect
public class LogerUserService {
    
    @Pointcut(value = "execution(public * sprig.aop.aop.UserService.*(sprig.aop.aop.helper.UserRequest))")
    public void logForUserService(){}

    @After(value = "logForUserService()")
    public void logUserService(JoinPoint joinPoint) {
        try {
            UserRequest userRequest = (UserRequest)joinPoint.getArgs()[0];
            log.info("firstName : "+userRequest.getFirstName()+" lastName : "+userRequest.getLastName()+" email : "+userRequest.getEmail());
        }
        catch (Throwable throwable) {
            throw throwable;
        }
        finally {
            log.info("done execute ...");
        }
    }
}
