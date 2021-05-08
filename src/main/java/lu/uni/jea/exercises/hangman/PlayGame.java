package lu.uni.jea.exercises.hangman;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import lu.uni.jea.exercises.hangman.MainClass;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 1 - Hangman
 *
 */

/**
 * Bean lives as long as the HTTP session lives.
 * It gets created upon the first HTTP request involving this bean in the session
 * and gets destroyed when the HTTP session is invalidated.
 */

@SessionScoped
@Named("play")

public class PlayGame  implements Serializable {

    private WordGenerator words;
    private String randomWord;
    private Character randomWordLetter;
    private LinkedHashMap<String, Integer> randomWordLetters = new LinkedHashMap<>();

    private String enteredLetter;
    private String lastAttemptedLetter;

    private int attemptsTotal;
    private int totalLetters;
    private int totalUniqLetters;
    private int guessedLettersTotal;
    private boolean winner;
    private String gameDisplay;

    private static final String PLAY = "play";

    public PlayGame() {
        start();
    }

    public void start() {

        System.out.println("-----------");
        System.out.println("Start the game");
        System.out.println("-----------");

        words = new WordGenerator();
        this.randomWord = words.getRandomWord();
        System.out.println("-----------");
        System.out.println("Generated word is : " + this.randomWord);
        System.out.println("-----------");

        totalLetters = randomWord.length();

        // Put all letters into a HashMap
        // with key is the letter and 0 or 1 as value to map if guessed or not
        // value is 0 by default

        System.out.println("-----------");
        for (int i = 0; i < totalLetters; i++) {
            // convert each char letter of the random word in String
            randomWordLetter = randomWord.charAt(i);
            System.out.println("Letter : " + randomWordLetter);
            randomWordLetters.put(randomWordLetter.toString(), 0);
        }
        System.out.println("-----------");

        System.out.println("-----------");
        for (Map.Entry<String, Integer> set : randomWordLetters.entrySet()) {
            System.out.println("HashMap value is : " + set.getKey() + " = " + set.getValue());
        }
        System.out.println("-----------");

        System.out.println("-----------");
        attemptsTotal = 0;
        System.out.println("Total attempts are : " + attemptsTotal);

        guessedLettersTotal = 0;
        System.out.println("Total guessed letters are : " + guessedLettersTotal);

        totalUniqLetters = randomWordLetters.size();
        System.out.println("Total letters are : " + totalLetters);
        System.out.println("Total uniq letters are : " + totalUniqLetters);
        System.out.println("-----------");

        winner = false;
        enteredLetter = "";

    }

    public String attempt() {
        attemptsTotal++;

        System.out.println("-----------");
        System.out.println("Total attempts are : " + attemptsTotal);
        System.out.println("Total guessed letters are : " + guessedLettersTotal);
        System.out.println("Total uniq letters are : " + totalUniqLetters);
        System.out.println("-----------");

        if(guessedLettersTotal == (totalUniqLetters - 1)) {
            winner =true;
        }
        return PLAY;
    }

    /** Getters and Setters */

    public String getRandomWord() {
        return this.randomWord;
    }

    /*
    Get game Display
    */

    public String getGameDisplay() {

        // Get the input
        StringBuffer buffer = new StringBuffer();

        // Total letters of the word to guess
        totalLetters = randomWord.length();

        System.out.println("-----------");
        System.out.println("guessedLettersTotal is : " + guessedLettersTotal);
        System.out.println("Entered letter is : " + enteredLetter);
        System.out.println("-----------");

        for (Map.Entry<String, Integer> set : randomWordLetters.entrySet()) {

            System.out.println("-----------");
            System.out.println("Letter in HashMap is: " + set.getKey());
            System.out.println("Entered letter is : " + enteredLetter);
            System.out.println("-----------");

            if(enteredLetter.equals(set.getKey())) {
                System.out.println("-----------");
                System.out.println("Letter in HashMap: " + set.getKey() + " match " + enteredLetter);
                System.out.println("Letter in HashMap: " + set.getKey() + " has value " + set.getValue());
                System.out.println("-----------");

                if (set.getValue() == 0) {
                    randomWordLetters.put(enteredLetter, 1);
                    guessedLettersTotal++;
                    System.out.println("-----------");
                    System.out.println("Letter in HashMap: " + set.getKey() + " match entered letter " + enteredLetter);
                    System.out.println("Letter in HashMap: " + set.getKey() + " has new value " + set.getValue());
                    System.out.println("-----------");
                }
            } else {
                System.out.println("-----------");
                System.out.println("Letter in HashMap: " + set.getKey() + " don't match entered letter " + enteredLetter);
                System.out.println("-----------");
            }
        }

        // If no attempts, to initialize the display
        if(attemptsTotal == 0) {

            System.out.println("-----------");
            System.out.println("Initial display");
            System.out.println("-----------");

            for (int i = 0; i < totalLetters; i++) {
                buffer.append("*");
            }

        } else {

            // Graph for all already guessed letters
            for(int i = 0; i < totalLetters; i++) {

                System.out.println("-----------");
                System.out.println("Build display for : " + randomWord.charAt(i));
                System.out.println("-----------");

                randomWordLetter = randomWord.charAt(i);

                if(randomWordLetters.containsKey(randomWordLetter.toString())) {

                    int randomWordLetterValue = randomWordLetters.get(randomWordLetter.toString());

                    System.out.println(randomWordLetter + " is in the HashMap with value " + randomWordLetterValue);

                    if(randomWordLetterValue == 1) {
                        System.out.println(randomWordLetter + " is in the HashMap and guessed. He is displayed");
                        buffer.append(randomWord.charAt(i));
                    } else {
                        System.out.println(randomWordLetter + " is not in the HashMap and is *");
                        buffer.append('*');
                    }
                }
            }
        }

        enteredLetter = "";
        gameDisplay = buffer.toString();
        return gameDisplay;
    }

    /*
    Is Winner ?
     */

    public boolean isWinner() {
        return winner;
    }

    /*
    Return the number of attempts
     */
    public int getAttemptsTotal() {
        return attemptsTotal;
    }

    /*
    Get the entered letter
     */
    public String getEnteredLetter() {
        return enteredLetter;
    }

    /*
    Get the last attempted letter
    */
    public String getLastAttemptedLetter() {
        return lastAttemptedLetter;
    }

    /*
    Set the entered letter
     */
    public void setEnteredLetter(String enteredLetter) {
        this.lastAttemptedLetter = enteredLetter.toUpperCase();
        this.enteredLetter = enteredLetter.toUpperCase();
    }

    /*
    Get size of the word to guess
     */

    public int getSize() {
        return randomWord.length();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
