package com.ntigo.junit5.demo;

import java.util.StringTokenizer;

public class Person {
    private static final int LIMIT_YEAR = 1983;

    private String name;
    private String birth;
    private String bloodType;
    private int age;
    private int tall;

    public Person( String name, String birth ) {
        this.name = name;
        this.birth = birth;

        StringTokenizer stringTokenizer = new StringTokenizer( birth, "." );
        int year = Integer.parseInt( stringTokenizer.nextToken() );

        System.out.println( year );
        if ( LIMIT_YEAR > year ) {
            throw new IllegalArgumentException( "get out!! noddang." );
        }
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth( String birth ) {
        this.birth = birth;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType( String bloodType ) {
        this.bloodType = bloodType;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public int getTall() {
        return tall;
    }

    public void setTall( int tall ) {
        this.tall = tall;
    }
}
