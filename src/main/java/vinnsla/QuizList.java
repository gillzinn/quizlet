package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuizList {
    protected ObservableList<Quiz> quizzes = FXCollections.observableArrayList();// listi öll quizin í quizlistanum


    /**
     * smiður á quizlistanum sem býr til 5 quiz og nokkrar spurningar í þau
     */
    public QuizList() {
        setQuiz("Enska", "Hvað þýðir Hello", "Hæ", "Bæ", "Nei", "Ja");
        setQuiz("Íslenska", "Hvað þýðir Hæ", "Hi", "Bye", "No", "Yes");
        setQuiz("Stærðfræði", "Hvað er: 9x9", "81", "72", "90", "99");
        setQuiz("Viðmótsforritun", "Hvað er gaman", "forrita", "læra", "lesa", "tapa");
        setQuiz("Enska 2", "Hvað ", "Hæ", "Bæ", "Nei", "Ja");
        quizzes.get(0).addSpurning("Hvað þýðir: Bye", "Bæ", "Bær", "Gær", "Hæ");
        quizzes.get(0).addSpurning("Hvað þýðir: Happy", "Glaður/Glöð", "Leiður/Leið", "Reiður/Reið", "Góður/Góð");
        quizzes.get(0).addSpurning("Hvað þýðir: Sad", "Leiður/Leið", "Glaður/Glöð", "Ánægður/Ánægð", "Pirraður/Pirruð");
        quizzes.get(0).addSpurning("Hvað þýðir: Funny", "Fyndinn/Fyndin", "Leiðinlegur/Leiðinleg", "Vondur/Vond", "Glaður/Glöð");
        quizzes.get(1).addSpurning("Hvað þýðir: Bæ", "Bye", "Hi", "Bi", "Like");
        quizzes.get(1).addSpurning("Hvað þýðir: Glaður", "Happy", "Sad", "Angry", "Hopeful");
        quizzes.get(1).addSpurning("Hvað þýðir: Maður", "Man", "Woman", "Food", "Many");
        quizzes.get(1).addSpurning("Hvað þýðir: Reiður", "Angry", "Sad", "Lonely", "Hopeful");
        quizzes.get(4).addSpurning("Hvað þýðir: Bye", "Bæ", "Bær", "Gær", "Hæ");
        quizzes.get(4).addSpurning("Hvað þýðir: Happy", "Glaður/Glöð", "Leiður/Leið", "Reiður/Reið", "Góður/Góð");
        quizzes.get(4).addSpurning("Hvað þýðir: Sad", "Leiður/Leið", "Glaður/Glöð", "Ánægður/Ánægð", "Pirraður/Pirruð");
        quizzes.get(4).addSpurning("Hvað þýðir: Funny", "Fyndinn/Fyndin", "Leiðinlegur/Leiðinleg", "Vondur/Vond", "Glaður/Glöð");
        quizzes.get(2).addSpurning("Hvað er: 7x7", "49", "77", "42", "56");
        quizzes.get(2).addSpurning("Hvað er: 8x8", "64", "88", "56", "72");
        quizzes.get(2).addSpurning("Hvað er: 11x11", "121", "110", "111", "122");
        quizzes.get(2).addSpurning("Hvað er: 12x5", "60", "125", "65", "58");
    }


    /**
     * fall sem býr til quiz
     *
     * @param nafn er nafn á quizinu
     * @param sp   er fyrsta spurningin í quizinu
     * @param sv   er svarið á spurningunni
     * @param a    er rangur svarmöguleiki 1
     * @param b    er rangur svarmöguleiki 2
     * @param c    er rangur svarmöguleiki 3
     */
    public void setQuiz(String nafn, String sp, String sv, String a, String b, String c) {
        Quiz q = new Quiz(nafn);
        q.addSpurning(sp, sv, a, b, c);
        quizzes.add(q);
    }


    /**
     * fall sem skilar lista af öllum quizunum
     *
     * @return
     */
    public ObservableList<Quiz> getQuizzes() {
        return quizzes;
    }

    /**
     * fall sem fær leitarorð og skilar öllum quizum sem heita það sama og leitarorðið
     *
     * @param leit er leitarstrengurinn
     * @return skilar lista af quizum sem heita það sama og leitarorðið
     */
    public ObservableList<Quiz> getQuizzesByName(String leit) {
        String ord = leit.toLowerCase();
        ObservableList<Quiz> quizleit = FXCollections.observableArrayList();
        for (Quiz q : quizzes) {
            int sum = 0;
            String nuverandiQ = q.getQuizName().toLowerCase();
            for (int i = 0; i < ord.length(); i++) {
                if (ord.length() > nuverandiQ.length()) {
                    break;
                } else {
                    if (nuverandiQ.charAt(i) == ord.charAt(i)) {


                        sum += 1;
                    }
                }
            }
            if (sum == ord.length()) {
                quizleit.add(q);
            }
        }
        return quizleit;
    }

    /**
     * fall sem nær í quiz með sama nafn og orðið
     *
     * @param ord nafn á quizi sem á að ná í
     * @return skilar quizi með rétt nafn
     */
    public Quiz getQuizByName(String ord) {
        Quiz qu = new Quiz("");
        for (Quiz q : quizzes) {
            if (q.getQuizName() == ord) {
                qu = q;
            }
        }
        return qu;
    }
}
