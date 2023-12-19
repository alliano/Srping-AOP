package sprig.aop.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component @Aspect @Slf4j
public class MultiplePoincut {
    
    @Pointcut(value = "execution(* sprig.aop.aop.service.*.*(..))")
    public void poinctuServicePackage() {}

    @Pointcut(value = "bean(*Service)")
    public void poincutServiceBean(){}

    @Pointcut(value = "execution(public * *(..))")
    public void poincutPublicMethod() {}

    @Pointcut(value = "poinctuServicePackage() && poincutServiceBean() && poincutPublicMethod()")
    public void publiucMethodService() {}

    @Before(value = "publiucMethodService()")
    public void logAllService() {
        log.info("Log all for public method service .................");
    }
}
