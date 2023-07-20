package com.example;

import com.example.utils.NumberGenerator;

import java.util.List;

public class StreamDemo {
    private final static int N_LOOPS = 10000;
    public static void main(String[] args) {
        List<Integer> list = NumberGenerator.getIntegerList();

        streamToListSize(list);
        streamToListIsEmpty(list);
        streamToArrayLength(list);
        streamFilterCount(list);
        streamAnyMatch(list);
    }

    private static void streamAnyMatch(List<Integer> list) {
        int counter = 0;
        for (int i = 0; i < N_LOOPS; i++) {
            if (list.stream().anyMatch(n -> n > 12)) {
                counter++;
            }
        }
        System.out.println("Running the stream statement using anyMatch " + counter);
    }

    private static void streamFilterCount(List<Integer> list) {
        int counter = 0;
        for (int i = 0; i < N_LOOPS; i++) {
            if (list.stream().filter(n -> n > 12).count() > 0) {
                counter++;
            }
        }
        System.out.println("Running the stream statement using filter and count " + counter);
    }

    private static void streamToArrayLength(List<Integer> list) {
        int counter = 0;
        for (int i = 0; i < N_LOOPS; i++) {
            if (list.stream().filter(n -> n > 12).toArray().length > 0) {
                counter++;
            }
        }
        System.out.println("Running the stream statement using toArray and length " + counter);
    }

    private static void streamToListIsEmpty(List<Integer> list) {
        int counter = 0;
        for (int i = 0; i < N_LOOPS; i++) {
            if (!list.stream().filter(n -> n > 12).toList().isEmpty()) {
                counter++;
            }
        }
        System.out.println("Running the stream statement using toList and isEmpty " + counter);
    }

    private static void streamToListSize(List<Integer> list) {
        int counter = 0;
        for (int i = 0; i < N_LOOPS; i++) {
            if (list.stream().filter(n -> n > 12).toList().size() > 0) { // some versions of intellij suggest .toList().isEmpty()
                counter++;
            }
        }
        System.out.println("Running the stream statement using toList and size " + counter);
    }

}