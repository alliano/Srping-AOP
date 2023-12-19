package sprig.aop.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration @Aspect @Slf4j
public class ParsingParam {

    @Pointcut(value = "execution(* sprig.aop.aop.Greeting.*(java.lang.String, java.lang.String))")
    public void poincutGreeting() {}

    @Before(value = "poincutGreeting() && args(greeting, name)")
    public void gretting(String greeting, String name) {
        log.info(greeting+" "+name);
    }
}
