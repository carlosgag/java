package com.multithreading;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Carlos on 13/12/2015.
 */
public class Calculator implements Runnable {

    private int number;

    public Calculator(int num) {
        this.number = num;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s: %d * %d = %d%n", Thread.currentThread().getName(), number, i, i * number);
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        Thread.State[] status = new Thread.State[10];
        for (int i = 0; i < 10; i++) {
            Calculator calculator = new Calculator(i);
            threads[i] = new Thread(calculator);
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread " + i);
        }
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (FileWriter file = new FileWriter(String.valueOf(classloader.getResource("resources\\log.txt")));
             PrintWriter writer = new PrintWriter(file);) {
            for (int i = 0; i < 10; i++) {
                writer.println("Main: Status of the Thread " + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }
            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }
            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != status[i]) {
                        writeThreadInfo(writer, threads[i], status[i]);
                        status[i] = threads[i].getState();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeThreadInfo(PrintWriter writer, Thread thread, Thread.State state) {
        writer.printf("Main : Id %d - %s%n", thread.getId(), thread.getName());
        writer.printf("Main : Priority: %d%n", thread.getPriority());
        writer.printf("Main : Old State: %s%n", state);
        writer.printf("Main : New State: %s%n", thread.getState());
        writer.printf("Main : ************************************%n");
    }
}
