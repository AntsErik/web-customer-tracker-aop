package ee.praktika.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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

    @Pointcut( "execution(* ee.praktika.springdemo.DAO.*.*(..))" )
    private void forDaoPackage(){
    }

    @Pointcut( "forControllerPackage() || forServicePackage() || forDaoPackage()" )
    private void forAppFlow(){
    }

    //add the before advice
    @Before( "forAppFlow()" )
    public void before( JoinPoint theJoinPoint ){

        //display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info( "\n===========>>>> in @Before: calling method: " + theMethod );

        //display the arguments to the method

        //get the arguments for the method call
        Object[] args = theJoinPoint.getArgs();

        //loop through and display those arguments.
        for( Object tempArg : args ) {
            myLogger.info( "\n===========>>>> argument: " + tempArg );
        }
    }

    //add the after-returning advice
    @AfterReturning( pointcut = "forAppFlow()", returning = "theResult" )
    public void afterReturning( JoinPoint theJoinPoint, Object theResult ){

        //display the method that is being returned
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info( "\n===========>>>> in @AfterReturning: calling method: " + theMethod );

        //display the data
        myLogger.info( "\n===========>>>> result: " + theResult );

    }

}
