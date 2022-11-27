package com.ntigo.junit5.demo;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class ExtensionModel implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private static final long THRESHOLD = 1000L;

    @Override
    public void beforeTestExecution( ExtensionContext context ) throws Exception {
        ExtensionContext.Store store = getStore( context );
        store.put( "StartTime", System.currentTimeMillis() );
    }

    @Override
    public void afterTestExecution( ExtensionContext context ) throws Exception {
        Method requiredTestMethod = context.getRequiredTestMethod();
        String methodName = requiredTestMethod.getName();

        Slow annotation = requiredTestMethod.getAnnotation( Slow.class );

        ExtensionContext.Store store = getStore( context );
        long startTime = store.remove( "StartTime", long.class );
        long duration = System.currentTimeMillis() - startTime;
        if ( duration > THRESHOLD && annotation == null ) {
            System.out.printf( "Please consider mark method [%s] with @SlowTest.\n", methodName );
        }
    }

    private static ExtensionContext.Store getStore( ExtensionContext context ) {
        String clazzName = context.getRequiredTestClass().getName();
        String methodName = context.getRequiredTestMethod().getName();
        return context.getStore( ExtensionContext.Namespace.create( clazzName, methodName ) );
    }
}
