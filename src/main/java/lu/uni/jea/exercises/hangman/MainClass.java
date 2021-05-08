package lu.uni.jea.exercises.hangman;



import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Bean lives as long as the HTTP session lives.
 * It gets created upon the first HTTP request involving this bean in the session
 * and gets destroyed when the HTTP session is invalidated.
 */

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 1 - Hangman
 *
 */

@SessionScoped
@Named("main")

public class MainClass implements Serializable {

    private static final String PLAY = "play";
    private static final String STOP = "stop";

    public MainClass() {

    }

    public String play() {
        System.out.println("Click on play button success");
        return PLAY;
    }

    public String stopGame() {
        System.out.println("Invalidate session to start new game");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return STOP;
    }

}
