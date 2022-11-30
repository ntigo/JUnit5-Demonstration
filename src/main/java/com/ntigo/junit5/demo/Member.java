package com.ntigo.junit5.demo;

import java.util.StringTokenizer;

public class Member {
    private static final int ENTRANCE_CUT_YEAR = 1983;

    private String name;
    private String birth;
    private int weight;
    private int height;

    public Member( String name, String birth ) {
        this.name = name;
        this.birth = birth;

        StringTokenizer stringTokenizer = new StringTokenizer( birth, "." );
        int year = Integer.parseInt( stringTokenizer.nextToken() );

        if ( year <= ENTRANCE_CUT_YEAR ) {
            throw new IllegalArgumentException( "get out!! old boy" );
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
