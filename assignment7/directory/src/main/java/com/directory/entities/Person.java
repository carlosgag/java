package com.directory.entities;

/**
 * Created by Carlos on 08/12/2015.
 */
public class Person {
    enum Position {
        ADJUNCT("Adjunct"),
        PROFESSOR("Professor"),
        STAFF("Staff"),
        FRESHMAN("Freshman"),
        SOPHOMORE("Sophomore"),
        JUNIOR("Junior"),
        SENIOR("Senior");

        private final String value;

        Position(String value) {
            this.value = value;
        }
    }

    private String ucid;
    private String first;
    private String last;
}
