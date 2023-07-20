package com.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class NumberGenerator {

    public static final String RANDOM_NUMBERS_PATH = "./data/random_numbers.txt";

    private List<Integer> numberList;
    private int currentIndex = 0;

    public NumberGenerator() {
        numberList = getIntegerList();
    }

    public Integer getNextNumber() {
        if(currentIndex == numberList.size())
            currentIndex = 0;
        return numberList.get(currentIndex++);
    }


    public static List<Integer> getIntegerList() {
        return getIntegerList(RANDOM_NUMBERS_PATH);
    }

    public static List<Integer> getIntegerList(String fileName) {
        List<Integer> integerList = new ArrayList<>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextInt()) {
                int n = s.nextInt();
                integerList.add(n);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        return integerList;
    }
}
