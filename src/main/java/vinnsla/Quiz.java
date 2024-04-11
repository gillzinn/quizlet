package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Quiz {
    private final ObservableList<Spurning> quiz; //listi af öllum spurningunum í quizinu
    private String quizName;// nafnið á quizinu


    /**
     * smiður á quizi
     *
     * @param name er nafnið sem quizið á að heita
     */
    public Quiz(String name) {
        quizName = name;
        this.quiz = FXCollections.observableArrayList();
    }

    /**
     * fall sem skilar öllum spurningum í quizi
     */
    public ObservableList<Spurning> getQuiz() {
        return quiz;
    }

    /**
     * fall sem skilar fjölda spurninga í quizi
     *
     * @return skilar int tölu sem táknar fjölda spurninga
     */
    public int getLength() {
        int sum = 0;
        for (Spurning s : quiz) {
            sum += 1;
        }
        return sum;
    }


    /**
     * fall sem skilar nafni á quiz hlut
     *
     * @return
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * fall sem býr til spurningu og setur hana í quizið
     *
     * @param sp er spurningin sjálf
     * @param sv er svarið
     * @param a  er rangur svarmöguleiki 1
     * @param b  er rangur svarmöguleiki 2
     * @param c  er rangur svar möguleiki 3
     */
    public void addSpurning(String sp, String sv, String a, String b, String c) {
        Spurning spur = new Spurning(sp, sv, a, b, c);
        quiz.add(spur);
    }

    /**
     * fall sem tekur við spurningu hlut og bætir honun í quizið
     *
     * @param ny
     */
    public void addSpurningNy(Spurning ny) {
        quiz.add(ny);
    }

}
