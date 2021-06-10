package fr.birdo.firemeloncore.utils;

public class Random {

    public Random(){
    }

    public static int roll(int min, int max) {
        int random = min + (int)(Math.random() * ((max - min) + 1));
        return random;
    }
}