package com.directory;

/**
 * Created by Carlos on 08/12/2015.
 */
public class DirectoryServer {
    public static void main(String [] args){
        Directory directory = new Directory();
        System.out.println("start --");
        directory.load();
        System.out.println("done --");

    }
}
