package com.pooh.moonrocks.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utils stands for utilities. It just contains a bunch of helper functions (methods) that can assist us anywhere in our
 * game.
 */
public class Utils {

    // We're going to make a function (method) that loads in a file for us, it takes in String path (the location of the
    // file on the computer).
    public static String loadFileAsString(String path) {
        // StringBuilder is an object that allows you to add characters to a String very easily.
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader( new FileReader(path) );

            String line;
            // Use BufferedReader to read in the next line and assign it to the String variable called line.
            // As long as (while) the next line is NOT null...
            // we append (add to the end) that line (plus a newline character "\n") to our StringBuilder called builder.
            while( (line = br.readLine()) != null ) {
                builder.append(line + "\n");
            }

            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return builder.toString();
    }

    // This second helper method is going to take in a String (like a String with the number 5 in it, "5") and it's
    // going to convert that into an integer value of 5 ("5" -> 5).
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            // If we try to pass in a String that is not a number (like "ABC"), it'll throw an error.
            ex.printStackTrace();   // Print the error to the screen (that way we know something happened).
            // @@@@ But we also want our program to NOT crash, so we'll return 0 as default. @@@@
            return 0;
        }
    }

} // **** end Utils class ****