package com.votingball.votingball.aspects;

import com.votingball.votingball.entity.Poll;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoggingCRUDAspect {

    @Pointcut("execution(* com.votingball.votingball.dao.*.findAll*(..))")
    private void findAll() {}

    @AfterReturning(pointcut = "findAll()",returning = "results")
    public void afterReturning(List<Poll> results)
    {
        System.out.println("Aspect trigerred.");
        results.forEach(System.out::println);
    }

}
