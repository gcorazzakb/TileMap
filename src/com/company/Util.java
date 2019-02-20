package com.company;

import java.util.Random;

public class Util {

    public  static String getRandomName(){
        byte[] name= new byte[10];
        new Random().nextBytes(name);
        return new String(name);
    }
}
