package lu.uni.jea.exercises.hangman;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 1 - Hangman
 *
 */

public class WordGenerator {

    //  Store all words into a text file
    private static final String fileName = "/res/words.txt";

    // Create an ArrayList to store the words
    private List<String> wordList = new ArrayList<String>();

    /* Constructor */
    public WordGenerator() {

        // Open the words file and add it to the ArrayList
        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {

            String line = "";
            while ((line = bf.readLine()) != null) {
                wordList.add(line);
            }
        } catch (Exception e) {
            System.out.println("File access error: " + fileName);
            System.out.println(" Error message: " + e.getMessage());
        }
    }

    public String getRandomWord() {
        // If the ArrayList is empty return "No Data"
        if (wordList.isEmpty())
            return "No Data";
        // else return

        return wordList.get((int) (Math.random() * wordList.size()));
    }

    // Public method to return the full words list
    public List<String> getWordList() {
        return wordList;
    }

    // Public method to check if the words list is empty
    public boolean isEmpty() {
        return wordList.isEmpty();
    }

}
