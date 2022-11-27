package com.ntigo.junit5.demo;

import java.util.StringTokenizer;

public class Person {
    private static final int LIMIT_YEAR = 1983;

    private String name;
    private String birth;
    private String bloodType;
    private int weight;
    private int height;

    public Person( String name, String birth ) {
        this.name = name;
        this.birth = birth;

        StringTokenizer stringTokenizer = new StringTokenizer( birth, "." );
        int year = Integer.parseInt( stringTokenizer.nextToken() );

        if ( LIMIT_YEAR > year ) {
            throw new IllegalArgumentException( "get out!! noddang." );
        }
    }

    public Person( String name, String birth, int height ) {
        this.name = name;
        this.birth = birth;
        this.height = height;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight( int weight ) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight( int height ) {
        this.height = height;
    }
}
