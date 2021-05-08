package lu.uni.jea.exercises.hangman;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

import lu.uni.jea.exercises.hangman.WordGenerator;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 1 - Hangman
 *
 */

@SessionScoped
@Named("main")

public class MainClass implements Serializable  {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final Logger logger = Logger.getLogger("loginBean");

    private WordGenerator words;
    private String randomWord;

    /** Creates new instance of MainClass */
    public MainClass() {
        this.words = new WordGenerator();
        this.randomWord = words.getRandomWord();
    }

    public String play() {
        if (words.isEmpty()) {
            return FAIL;
        }
        return SUCCESS;
    }

    /** Getters and Setters */
    public String getRandomWord() {
        return this.randomWord;
    }

    public void setRandomWord(String randomWord) {
        this.randomWord = randomWord;
    }
}
