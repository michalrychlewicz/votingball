package com.votingball.votingball.aspects;

import com.votingball.votingball.entity.Poll;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingCRUDAspect {

    @Pointcut("execution(* com.votingball.votingball.dao.*.save*(..))")
    private void beforeInsert(){}

    @Before("beforeInsert()")
    public void beforeInsertion(JoinPoint joinPoint)
    {
        Object[] captured = joinPoint.getArgs();
        if(captured != null)
        {
           for(Object object : captured)
           {
               if(object instanceof Poll)
               {
                   Poll poll = (Poll) object;
                   System.out.println("Poll being saved: "+poll);
               }
           }
        }
    }

}
