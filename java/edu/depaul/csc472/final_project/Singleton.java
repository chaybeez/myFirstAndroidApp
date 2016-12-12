package edu.depaul.csc472.final_project;


import java.util.HashMap;
import java.util.List;

public class Singleton {
    private static Singleton theInstance;


    //the number of questions left
    public int counter;
    //the number of questions correct
    public int correct;
    //the current username
    public String name;
    //the number selected for multiplication tests
    public String num;
    //the list of questions to ask
    List<String> questions;
    //hashmap for answers
    HashMap<String, Integer> hm;

    public static Singleton getInstance() {
        if (theInstance == null) {
            theInstance = new Singleton();
        }
        return theInstance;
    }


    private Singleton() {
    }
}
