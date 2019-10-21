package ee.praktika.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger( getClass().getName() );

    //setup pointcut declarations
    @Pointcut( "execution(* ee.praktika.springdemo.controller.*.*(..))" )
    private void forControllerPackage(){
    }

    @Pointcut( "execution(* ee.praktika.springdemo.service.*.*(..))" )
    private void forServicePackage(){
    }

    @Pointcut( "execution(* ee.praktika.springdemo.dao.*.*(..))" )
    private void forDaoPackage(){
    }

    @Pointcut( "forControllerPackage() || forServicePackage() || forDAOPackage()" )
    private void forAppFlow(){
    }

    //add the before advice
    @Before( "forAppFlow()" )
    public void before( JoinPoint theJoinPoint ){

        //display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info( "\n===========>>>> in @Before: calling method: " + theMethod );

        //displat the arguments to the method

    }

    //add the afterreturning advice

}
