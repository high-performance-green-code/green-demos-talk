package com.example;

import com.example.utils.NumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class CollectionAllocationDemo {

    private final static int N_LOOPS = 10000;
    private final static int LIST_SIZE = 15000;

    public static void main(String[] args) {
        NumberGenerator numberGenerator = new NumberGenerator();
        nonPreAllocatedCollectionDemo(numberGenerator);
        preallocatedCollectionDemo(numberGenerator);
    }

    private static void nonPreAllocatedCollectionDemo(NumberGenerator numberGenerator) {

        int result = 0;
        for (int i = 0; i < N_LOOPS; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < LIST_SIZE; j++) {
                list.add(numberGenerator.getNextNumber());
            }
            result += list.size();
        }
        System.out.println("non pre-allocated result value: " + result);
    }

    private static void preallocatedCollectionDemo(NumberGenerator numberGenerator) {
        int result = 0;
        for (int i = 0; i < N_LOOPS; i++) {
            List<Integer> list = new ArrayList<>(LIST_SIZE);
            for (int j = 0; j < LIST_SIZE; j++) {
                list.add(numberGenerator.getNextNumber());
            }
            result += list.size();
        }
        System.out.println("pre-allocated result value: " + result);
    }
}
