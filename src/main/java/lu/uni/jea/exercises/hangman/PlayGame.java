package lu.uni.jea.exercises.hangman;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

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

    private String enteredLetter;
    private String lastAttemptedLetter;
    private int attemptsTotal;
    private int totalLetters;
    private int guessedLetters;
    private boolean winner;
    private String gameDisplay;

    private static final String PLAY = "play";

    public PlayGame() {
        start();
    }

    public void start() {

        System.out.println("Start the game");

        words = new WordGenerator();
        this.randomWord = words.getRandomWord();
        System.out.println("Generated word is : " + this.randomWord);

        attemptsTotal = 0;
        System.out.println("attemptsTotal is : " + attemptsTotal);
        guessedLetters = 0;
        System.out.println("guessedLetters is : " + guessedLetters);
        winner = false;
        enteredLetter = "";
    }

    public String attempt() {
        attemptsTotal++;
        if (randomWord.equals(enteredLetter)) {
            winner = true;
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
        System.out.println("totalLetters is : " + totalLetters);
        System.out.println("guessedLetters is : " + guessedLetters);

        if(guessedLetters == 0) {
            System.out.println("In the buffer loop for guessedLetters = " + guessedLetters);
            for (int i = 0; i < totalLetters; i++) {
                buffer.append("*");
            }
        } else {
            System.out.println("In the buffer loop for guessedLetters = " + guessedLetters);
            for (int i = 0; i < totalLetters; i++) {
                if (randomWord.charAt(i) == enteredLetter.charAt(i)) {
                    buffer.append(randomWord.charAt(i));
                } else {
                    buffer.append('*');
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
        this.lastAttemptedLetter = enteredLetter;
        this.enteredLetter = enteredLetter;
    }

    /*
    Get size of the word to guess
     */

    public int getSize() {
        return randomWord.length();
    }
}
