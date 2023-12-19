package sprig.aop.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component @Aspect
public class LoggerAspect {
    
    @Pointcut(value = "target(sprig.aop.aop.ApplicationService)")
    public void applicationSercviceHello() {}

    @Pointcut(value = "execution(* sprig.aop.aop.ApplicationLogerAround.*(..))")
    public void applicatonAroundPoincut() {}

    @Before(value = "applicationSercviceHello()")
    public void beforeApplicationService(JoinPoint joinPoint) {
        // classname
        String methodName = joinPoint.getSignature().getName();
        // class name
        String className = joinPoint.getTarget().getClass().getName();
        
        log.info("before "+className+"."+methodName+"()");
        log.info("Execution before applicationService");
    }
    
    @Around(value = "applicatonAroundPoincut()")
    public Object aroundLoger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // Method name
        String methodName = proceedingJoinPoint.getSignature().getName();
        // class name
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        try {
            String arg = ((String)proceedingJoinPoint.getArgs()[0]);
            log.info("Value form argument : "+arg);
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable throwable) {
            log.info("Around Error " + className + "." + methodName + "()");
            throw throwable;
        }
        finally {
            log.info("Around finaly " + className + "." + methodName + "()");
        }
    }
}
