package com.multithreading;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Carlos on 16/12/2015.
 */
public class FileSearch implements Runnable {

    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            File file = new File(initPath);
            if (file.isDirectory()) {
                directoryProcess(file);
            } else {
                fileProcess(file);
            }
        } catch (InterruptedException e) {
            System.out.printf("%s: The search has been interrupted",
                    Thread.currentThread().getName());
        }
    }

    private void directoryProcess(File file)
            throws InterruptedException {
        System.out.printf("Processing %s ...%n",file.getName());
        File list[] = file.listFiles();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].isDirectory()) {
                    directoryProcess(list[i]);
                } else {
                    fileProcess(list[i]);
                }
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    private void fileProcess(File file)
            throws InterruptedException {
        if (file.getName().equals(fileName)) {
            System.out.printf("%s : %s\n",
                    Thread.currentThread().getName(), file.getAbsolutePath());
            Thread.currentThread().interrupt();
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }

    }

    public static void main(String[] args) {
        FileSearch searcher=new FileSearch("C:\\Sierra","8ball1.bmp");
        Thread thread=new Thread(searcher);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
