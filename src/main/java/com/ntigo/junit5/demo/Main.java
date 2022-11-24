package com.ntigo.junit5.demo;

public class Main {
    public static void b() {
        a();
    }
    public static void a() {
        int afdasf = Integer.parseInt( "AFDASF" );
        System.out.println( "111");
    }
    public static void main( String[] args ) {
        Runnable runnable = () -> {
            Person person = null;
            person.getBirth();

            System.out.println( "test" );
        };

        Thread thread = new Thread( runnable );
        thread.setUncaughtExceptionHandler( new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException( Thread t, Throwable e ) {
                System.out.println( e.getMessage() );
            }
        } );
        thread.start();


        try {
            Person person = null;
            person.getBirth();
        } catch ( Exception e ) {

        }
        finally {
            System.out.println("finally");
            System.out.println( "end ");
        }

        try {
            thread.join(5000);
        } catch ( InterruptedException e ) {
            throw new RuntimeException( e );
        }

//        a();

        b();

        int a = 0;
        System.out.println( a );
    }
}