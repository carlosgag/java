package com.directory;

import com.directory.entities.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Carlos on 08/12/2015.
 */
public class Directory {

    List<String> records = new ArrayList<>();
    List<Person> persons = new ArrayList<>();

    public void load() {
        getLines();
    }

    public void getLines() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("UniversityDirectoryA.txt");
        try {
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String read = br.readLine();
            while (read != null) {
                read = br.readLine();
                records.add(read);
            }
            Iterator<String> it = records.iterator();
            while (it.hasNext()) {
                System.out.println(it.next() + "<<  ");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
