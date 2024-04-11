package vidmot;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import vinnsla.Quiz;
import vinnsla.QuizList;
import vinnsla.Spurning;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class QuizLetController extends QuizList {

    @FXML
    private Button fxButton1;// takki 1 af 4 í test grid
    @FXML
    private Button fxButton2;// takki 2 af 4 í test grid
    @FXML
    private Button fxButton3;// takki 3 af 4 í test grid
    @FXML
    private Button fxButton4;// takki 4 af 4 í test grid
    @FXML
    private Label fxSpurning;//label þar sem spurningin er prentuð
    @FXML
    private Button fxNaesta;// takki sem nær í næstu spurningu
    @FXML
    private Button fxTilbaka;// takki sem nær í fyrri spurningu
    @FXML
    private Button fxSkodaSvar;// takki sem sýnir svar
    @FXML
    private TextField fxLeit;// search barið uppi
    @FXML
    private Label fxSynaSvar;//labelið sem prentar svarið út
    @FXML
    private Pane fxSearchArea;//Paneið sem inniheldur search barið

    @FXML
    private Label fxBrowseDiv1;// 1 af 4 labels fyrir browse síðuna sem prentar út nafn á quizi
    @FXML
    private Label fxBrowseDiv2;// 2 af 4 labels fyrir browse síðuna sem prentar út nafn á quizi
    @FXML
    private Label fxBrowseDiv3;// 3 af 4 labels fyrir browse síðuna sem prentar út nafn á quizi
    @FXML
    private Label fxBrowseDiv4;// 4 af 4 labels fyrir browse síðuna sem prentar út nafn á quizi

    @FXML
    private Button fxTilTest;//takki sem færir þig í test
    @FXML
    private Label fxTestAgain;//takki sem færir þig frá study í test
    @FXML
    private Label fxEinkunn;//label sem prentar út einkunn í lok test

    @FXML
    private Button fxNewQuestion;//takki sem nær í nýspurningDialog og gerir þér kleyft að bæta við spurningu

    @FXML
    private TextField fxCreateQuizName;//nafnið á nýja quizinu
    @FXML
    private TextField fxCreateQuestion;// fyrsta spurningin á quizi sem á að búa til
    @FXML
    private TextField fxCreateAnswer;// svarið
    @FXML
    private TextField fxCreateWrongAnswer1;//rangur svarmöguleiki 1
    @FXML
    private TextField fxCreateWrongAnswer2;// rangur svarmöguleiki 2
    @FXML
    private TextField fxCreateWrongAnswer3;// rangur svarmöguleiki 3

    @FXML
    private Button fxConfirmCreate;//staðfestingar hnappur sem sendir upplýsingarnar á nýju quizinu

    @FXML
    private ListView<String> fxQuizzezList;//listview í search area sem sýnir quizin í leit

    @FXML
    private Label fxFjoldiSp;// label sem sýnir fjölda spurninga í quiz
    @FXML
    private Pane fxSearchDrop;//paneið sem droppar niður þegar ýtt er á search
    @FXML
    private BorderPane fxAdalBorder;// borderið sem þegar ýtt er á það í search þá hverfur search paneið
    @FXML
    private VBox fxLeitDrop;//vbox sem inniheldur quizlist í search area
    @FXML
    private Pane fxBackSearch;// back arrow til að fela search area

    @FXML
    private Pane fxCardFlip;//cardið sem sýnir spurningu í study sem hægt er að flippa til að sjá svar
    @FXML
    private Label fxCardFlipLabel;// labelið sem prentar út spurninguna á cardið

    @FXML
    private Label fxFjoldiSida;// fjöldi síða í browse

    @FXML
    private Pane fxArrowLeft;// back arrow í browse á milli síða
    @FXML
    private Pane fxArrowRight;// next arrow í browse á milli síða

    @FXML
    private Label fxBdiv1Study;// label 1 sem gerir kleyft að fara í study með þessu quizi ef ýtt er á það
    @FXML
    private Label fxBdiv1Test;// label 2 sem gerir kleyft að fara í test með þessu quizi ef ýtt er á það
    @FXML
    private Label fxBdiv2Study;// label 1 sem gerir kleyft að fara í study með þessu quizi ef ýtt er á það
    @FXML
    private Label fxBdiv2Test;// label 2 sem gerir kleyft að fara í test með þessu quizi ef ýtt er á það
    @FXML
    private Label fxBdiv3Study;// label 1 sem gerir kleyft að fara í study með þessu quizi ef ýtt er á það
    @FXML
    private Label fxBdiv3Test;// label 2 sem gerir kleyft að fara í test með þessu quizi ef ýtt er á það
    @FXML
    private Label fxBdiv4Study;// label 1 sem gerir kleyft að fara í study með þessu quizi ef ýtt er á það
    @FXML
    private Label fxBdiv4Test;// label 2 sem gerir kleyft að fara í test með þessu quizi ef ýtt er á það

    @FXML
    private Label fxQuizName;//label sem prentar út nafnið á quizinu

    private Spurning nuverandiSpurning;//núverandi spurning sem er sýnd
    private Quiz nuverandiQuiz;//núverandi quiz sem er sýnt
    private QuizList quizlist = new QuizList();//listinn af öllum quizum

    private int i = 0;//teljari
    private int validQuiz;//valid quiz index í lista

    private int browseCount;// teljari fyrir núverandi síðu í browse
    private int fjoldisida = 0;// fjöldi síða í browse
    private int fjoldirett;// fjöldi spurningum svarað rétt

    @FXML
    private Pane fxBdiv1;// card 1 af 4 í browse div sem sýna quizin
    @FXML
    private Pane fxBdiv2;// card 2 af 4 í browse div sem sýna quizin
    @FXML
    private Pane fxBdiv3;// card 3 af 4 í browse div sem sýna quizin
    @FXML
    private Pane fxBdiv4;// card 4 af 4 í browse div sem sýna quizin

    private Label node;//node sem er hoveruð yfir í browse


    /**
     * initialize method sem stillir search barið og ýmsa aðra fídusa
     */
    public void initialize() {
        leitaAfQuiz("");
        fxLeit.caretPositionProperty().addListener((obs, oldPosition, newPosition) -> {
                    String t = fxLeit.getText().substring(0, newPosition.intValue());
                    if (newPosition.intValue() == 0) {
                        leitaAfQuiz("");
                    }
                    if (t.length() - 1 < 0) {
                        return;
                    }
                    for (i = t.length() - 1; i >= 0; i--) {
                        String ut = t.substring(i);
                        leitaAfQuiz(ut);
                    }
                }

        );

        fxQuizzezList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            validQuiz = fxQuizzezList.getSelectionModel().getSelectedIndex();
            StudyonSearch(quizlist.getQuizzes().get(validQuiz));
        });
        fxLeit.getStyleClass().clear();
        fxLeit.getStyleClass().add("Searchfield");

        fxSearchArea.setOnMousePressed(event -> {
            fxSearchDrop.setVisible(true);
            fxLeit.setVisible(true);
            fxLeit.setEditable(true);
            fxLeitDrop.setVisible(true);


        });
        fxAdalBorder.setOnMousePressed(event -> {
            fxSearchDrop.setVisible(false);
            fxLeit.setVisible(false);
            fxLeitDrop.setVisible(false);
            fxLeit.setEditable(false);
        });

        fxBackSearch.setOnMousePressed(event -> {
            fxSearchDrop.setVisible(false);
            fxLeit.setVisible(false);
            fxLeitDrop.setVisible(false);
            fxLeit.setEditable(false);
        });

    }


    /**
     * fall sem initializear browse síðuna með quizlista
     *
     * @param quizl listi af quizum sem á að sýna á síðunni
     */
    public void initializeBrowse(QuizList quizl) {
        quizlist = quizl;
        leitaAfQuiz("");
        browseCount = 1;
        fxBrowseDiv1.setText(quizlist.getQuizzes().get(0).getQuizName());
        fxBrowseDiv2.setText(quizlist.getQuizzes().get(1).getQuizName());
        fxBrowseDiv3.setText(quizlist.getQuizzes().get(2).getQuizName());
        fxBrowseDiv4.setText(quizlist.getQuizzes().get(3).getQuizName());

        int fjoldiq = quizlist.getQuizzes().size();

        if (fjoldiq % 4 == 0) {
            fjoldisida = quizlist.getQuizzes().size() / 4;

        } else {
            int rem = fjoldiq % 4;
            int r = fjoldiq - rem;
            fjoldisida = r / 4 + 1;
        }
        if (fjoldisida > 1 && browseCount != fjoldisida) {
            fxArrowRight.setOnMouseClicked(event -> {
                NaestaBrowseSida();
            });
        }

        fxFjoldiSida.setText(browseCount + "/" + fjoldisida);


    }

    /**
     * fall sem initiallizear create síðuna með núverandi quizlista
     *
     * @param quizl listi af quizum sem á að uppfæra
     */
    public void initializeCreate(QuizList quizl) {
        quizlist = quizl;
        leitaAfQuiz("");
        fxConfirmCreate.disableProperty().bind(fxCreateQuizName.textProperty().isEmpty().or(fxCreateQuestion.textProperty().isEmpty().or(fxCreateAnswer.textProperty().isEmpty().or(fxCreateWrongAnswer1.textProperty().isEmpty().or(fxCreateWrongAnswer2.textProperty().isEmpty().or(fxCreateWrongAnswer3.textProperty().isEmpty()))))));

    }


    /**
     * fall sem initializear test síðuna með völdu quizi, og birtir það
     */
    public void initializeTestWithQuiz(Quiz quiz, QuizList quizl) {
        quizlist = quizl;
        leitaAfQuiz("");
        synaQuiz(quiz.getQuizName());
        fxButton1.setOnAction(this::athugaSvar);
        fxButton2.setOnAction(this::athugaSvar);
        fxButton3.setOnAction(this::athugaSvar);
        fxButton4.setOnAction(this::athugaSvar);
        fjoldirett = 0;

        fxEinkunn.setText("");
        fxTestAgain.setVisible(false);

    }

    /**
     * fall sem initializear test síðuna með quizlista, og birtir það
     */
    public void initializeTest(QuizList quizl) {
        quizlist = quizl;
        leitaAfQuiz("");
    }

    /**
     * fall sem initializear study síðuna með völdu quizi, og birtir það
     */
    public void initializeStudyWithQuiz(Quiz quiz, QuizList quizl) {
        quizlist = quizl;
        leitaAfQuiz("");
        synaStudyQuiz(quiz.getQuizName());
        fxCardFlip.setOnMouseClicked(event -> {
            flipStudySpurning();
        });

        fxTilTest.setOnAction(actionEvent -> {
            TestfromStudy();
        });

        fxNewQuestion.setOnAction(actionEvent -> {
            NewQuestion();
        });
    }

    /**
     * fall sem initializear study síðuna með quizlista, og birtir það
     */
    public void initializeStudy(QuizList quizl) {
        quizlist = quizl;
        leitaAfQuiz("");
    }


    /**
     * fall sem breytir í browse síðuna
     */
    public void Browse() {
        ViewSwitcher.switchTo(View.BROWSE, quizlist);

    }

    /**
     * fall sem breytir í study síðuna
     */
    public void Study() {
        ViewSwitcher.switchTo(View.STUDY, quizlist);
    }

    /**
     * fall sem breytir í study síðuna með völdu quizi frá search
     *
     * @param valid quiz sem á að birta
     */
    public void StudyonSearch(Quiz valid) {
        ViewSwitcher.switchTo(View.STUDY, valid, quizlist);
    }


    /**
     * fall sem breytir í study siðuna frá browse síðunni með völdu quizi
     */
    public void StudyonClick() {
        ViewSwitcher.switchTo(View.STUDY, quizlist.getQuizByName(node.getText()), quizlist);
    }

    /**
     * fall sem breytir í test síðuna
     */
    public void Test() {
        ViewSwitcher.switchTo(View.TEST, quizlist);

    }

    /**
     * fall sem breytir í test síðuna frá browse síðunni með völdu quizi
     */
    public void TestonClick() {
        ViewSwitcher.switchTo(View.TEST, quizlist.getQuizByName(node.getText()), quizlist);

    }

    /**
     * fall sem breytir í test síðuna frá study síðunni með núverandi quizi
     */
    public void TestfromStudy() {
        ViewSwitcher.switchTo(View.TEST, quizlist.getQuizByName(nuverandiQuiz.getQuizName()), quizlist);

    }

    /**
     * fall sem breytir í create síðuna
     */
    public void Create() {
        ViewSwitcher.switchTo(View.CREATE, quizlist);

    }

    /**
     * fall sem sýnir upplýsingar um quiz í study síðunni
     *
     * @param s er nafn á quizi sem á að sýna
     */
    public void synaStudyQuiz(String s) {
        i = 0;
        nuverandiQuiz = quizlist.getQuizByName(s);
        nuverandiSpurning = nuverandiQuiz.getQuiz().get(i);
        fxQuizName.setText(nuverandiQuiz.getQuizName());
        synaStudySpurning(nuverandiSpurning);


    }

    /**
     * fall sem sýnir quiz í test síðunni
     *
     * @param s nafn á quizi sem á að sýna
     */
    public void synaQuiz(String s) {
        i = 0;
        nuverandiQuiz = quizlist.getQuizByName(s);
        nuverandiSpurning = nuverandiQuiz.getQuiz().get(i);
        synaSpurning(nuverandiSpurning);
    }


    /**
     * fall sem sýnir spurningu í test síðunni
     *
     * @param e er spurningin
     */
    public void synaSpurning(Spurning e) {
        fxNaesta.setDisable(true);

        String[] Ar = e.getRongsvor();

        List<String> List = Arrays.asList(Ar);
        Collections.shuffle(List);
        List.toArray(Ar);

        int ii = i + 1;

        fxFjoldiSp.setText(ii + "/" + nuverandiQuiz.getLength());
        fxSpurning.setText(e.getSpurning());
        fxButton1.setText(Ar[0]);
        fxButton2.setText(Ar[1]);
        fxButton3.setText(Ar[2]);
        fxButton4.setText(Ar[3]);
        enableButtons();
        fxSkodaSvar.setDisable(true);
        fxSynaSvar.setText("");
        fxQuizName.setText(nuverandiQuiz.getQuizName());

    }

    /**
     * fall sem sýnir spurningu í study síðunni
     *
     * @param e er spurningin
     */
    public void synaStudySpurning(Spurning e) {
        String sp = e.getSpurning();
        fxCardFlipLabel.setText(sp);
        int ii = i + 1;
        fxFjoldiSp.setText(ii + "/" + nuverandiQuiz.getLength());
        if (ii == nuverandiQuiz.getLength()) {
            fxNaesta.setOnAction(null);
        } else {
            fxNaesta.setOnAction(actionEvent -> {
                NaestaStudy();
            });
        }
        if (ii == 1) {
            fxTilbaka.setOnAction(null);
        } else {
            fxTilbaka.setOnAction(actionEvent -> {
                FyrriStudy();
            });
        }
    }

    /**
     * fall sem breytir texta á cardinu sem flippast í study
     */
    public void changeText() {

        String sp = nuverandiSpurning.getSpurning();
        if (fxCardFlipLabel.getText().equals(sp)) {

            fxCardFlipLabel.setText(nuverandiSpurning.getSvar());
        } else {
            fxCardFlipLabel.setText(nuverandiSpurning.getSpurning());
        }

        rotateIn(fxCardFlip, fxCardFlipLabel);
    }

    /**
     * fall sem breytir texta á cardinu til baka, sem flippast í study
     *
     * @param s er upplýsingar hvor megin cardið er flippað
     */
    public void changeTexttilBaka(String s) {
        if (s.equals("left")) {
            SynaFyrriStudy();
        } else {
            SynaNaestaStudy();
        }
        rotateIn(fxCardFlip, fxCardFlipLabel);
    }


    /**
     * fall sem flippar cardinu inn
     *
     * @param n    nodean sem á að flippa
     * @param text textinn á nodeunni sem á að flippa og breyta
     */
    public void rotateIn(Node n, Node text) {
        RotateTransition rotateIn = new RotateTransition();
        rotateIn.setNode(n);
        rotateIn.setDuration(Duration.millis(250));
        rotateIn.setCycleCount(1);
        rotateIn.setInterpolator(Interpolator.LINEAR);
        rotateIn.setByAngle(90);
        rotateIn.setAxis(Rotate.X_AXIS);
        rotateIn.play();

        RotateTransition rotateText = new RotateTransition();
        rotateText.setNode(text);
        rotateText.setDuration(Duration.millis(10));
        rotateText.setCycleCount(1);
        rotateText.setInterpolator(Interpolator.LINEAR);
        rotateText.setByAngle(-180);
        rotateText.setAxis(Rotate.X_AXIS);
        rotateText.play();

    }


    /**
     * fall sem flippar  cardinu
     */
    public void flipStudySpurning() {

        RotateTransition rotateOut = new RotateTransition();
        rotateOut.setNode(fxCardFlip);
        rotateOut.setDuration(Duration.millis(250));
        rotateOut.setCycleCount(1);
        rotateOut.setInterpolator(Interpolator.LINEAR);
        rotateOut.setByAngle(90);
        rotateOut.setAxis(Rotate.X_AXIS);
        rotateOut.setOnFinished(e -> changeText());
        rotateOut.play();

    }

    /**
     * fall sem flippar cardinu til baka inn
     *
     * @param s er upplýsingar hvor megin cardið snýr
     */
    public void flipStudySpurningTilbaka(String s) {

        RotateTransition rotateOut = new RotateTransition();
        rotateOut.setNode(fxCardFlip);
        rotateOut.setDuration(Duration.millis(250));
        rotateOut.setCycleCount(1);
        rotateOut.setInterpolator(Interpolator.LINEAR);
        rotateOut.setByAngle(90);
        rotateOut.setAxis(Rotate.X_AXIS);
        rotateOut.setOnFinished(e -> changeTexttilBaka(s));
        rotateOut.play();


    }

    /**
     * fall sem býr til flott hover effect á cardunum í browse
     *
     * @param event er event þegar músin hoverar yfir card
     */
    public void viewCard(MouseEvent event) {
        Pane source = (Pane) event.getTarget();
        String id = source.getId();
        Label n;
        if (id.equals("fxBdiv1")) {
            n = fxBrowseDiv1;
            fxBdiv1Study.setVisible(true);
            fxBdiv1Test.setVisible(true);
            fxBrowseDiv1.setVisible(false);
        } else if (id.equals("fxBdiv2")) {
            n = fxBrowseDiv2;
            fxBdiv2Study.setVisible(true);
            fxBdiv2Test.setVisible(true);
            fxBrowseDiv2.setVisible(false);
        } else if (id.equals("fxBdiv3")) {
            n = fxBrowseDiv3;
            fxBdiv3Study.setVisible(true);
            fxBdiv3Test.setVisible(true);
            fxBrowseDiv3.setVisible(false);
        } else {
            n = fxBrowseDiv4;
            fxBdiv4Study.setVisible(true);
            fxBdiv4Test.setVisible(true);
            fxBrowseDiv4.setVisible(false);
        }


        node = n;


        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(source);
        scaleTransition.setDuration(Duration.millis(250));
        scaleTransition.setInterpolator(Interpolator.LINEAR);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    /**
     * fall sem lagar cardið þegar músin fer af því
     *
     * @param event er event þegar músin hoverar af cardinu
     */
    public void leaveCard(MouseEvent event) {
        Pane source = (Pane) event.getTarget();
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(source);
        scaleTransition.setDuration(Duration.millis(250));
        scaleTransition.setInterpolator(Interpolator.LINEAR);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();


        if (node == fxBrowseDiv1) {
            fxBdiv1Study.setVisible(false);
            fxBdiv1Test.setVisible(false);
            fxBrowseDiv1.setVisible(true);
        } else if (node == fxBrowseDiv2) {
            fxBdiv2Study.setVisible(false);
            fxBdiv2Test.setVisible(false);
            fxBrowseDiv2.setVisible(true);
        } else if (node == fxBrowseDiv3) {
            fxBdiv3Study.setVisible(false);
            fxBdiv3Test.setVisible(false);
            fxBrowseDiv3.setVisible(true);
        } else {
            fxBdiv4Study.setVisible(false);
            fxBdiv4Test.setVisible(false);
            fxBrowseDiv4.setVisible(true);
        }


    }

    /**
     * fall sem athugar hvort svarið sem notandi valdi er rétt svar
     *
     * @param event er event sem gefur upplýsingar hvaða svar notandi valdi
     */
    public void athugaSvar(Event event) {

        Button source = (Button) event.getTarget();
        source.getStyleClass().remove("Button");
        if (source.getText().equals(nuverandiSpurning.getSvar())) {
            source.getStyleClass().add("Green");
            disableButtons();
            fjoldirett += 1;
        } else {
            source.getStyleClass().add("Red");
            disableButtons();
            fxSkodaSvar.setDisable(false);

        }
        fxNaesta.setDisable(false);

        double reikna = ((double) fjoldirett / (nuverandiQuiz.getLength())) * 10;
        if ((i) == nuverandiQuiz.getLength()) {
            fxEinkunn.setText("Þú náðir " + fjoldirett + " af " + nuverandiQuiz.getLength() + " rétt og " + reikna + " í einkunn.");
            fxTestAgain.setVisible(true);
            fxTestAgain.setOnMouseClicked(event1 -> {
                initializeTestWithQuiz(nuverandiQuiz, quizlist);
            });
        }

        if (i == nuverandiQuiz.getLength()) {
            fxNaesta.setDisable(true);
        }

    }

    /**
     * fall sem disablear takka sem eiga ekki að vera í notkun
     */
    public void disableButtons() {
        fxButton1.setDisable(true);
        fxButton2.setDisable(true);
        fxButton3.setDisable(true);
        fxButton4.setDisable(true);
        fxButton1.getStyleClass().add("Darken");
        fxButton2.getStyleClass().add("Darken");
        fxButton3.getStyleClass().add("Darken");
        fxButton4.getStyleClass().add("Darken");
        i += 1;
        fxNaesta.setDisable(false);
        fxSkodaSvar.setDisable(false);
    }

    /**
     * fall sem enablear takka sem eiga að vera í notkun
     */
    public void enableButtons() {
        fxButton1.setDisable(false);
        fxButton2.setDisable(false);
        fxButton3.setDisable(false);
        fxButton4.setDisable(false);
        fxButton1.getStyleClass().remove("Darken");
        fxButton2.getStyleClass().remove("Darken");
        fxButton3.getStyleClass().remove("Darken");
        fxButton4.getStyleClass().remove("Darken");
        fxButton1.getStyleClass().remove("Green");
        fxButton2.getStyleClass().remove("Green");
        fxButton3.getStyleClass().remove("Green");
        fxButton4.getStyleClass().remove("Green");
        fxButton1.getStyleClass().remove("Red");
        fxButton2.getStyleClass().remove("Red");
        fxButton3.getStyleClass().remove("Red");
        fxButton4.getStyleClass().remove("Red");
        fxButton1.getStyleClass().add("Button");
        fxButton2.getStyleClass().add("Button");
        fxButton3.getStyleClass().add("Button");
        fxButton4.getStyleClass().add("Button");
    }

    /**
     * fall sem nær í næstu spurningu í test
     */
    public void Naesta() {
        nuverandiSpurning = nuverandiQuiz.getQuiz().get(i);
        synaSpurning(nuverandiSpurning);
    }

    /**
     * fall sem nær í næstu spurningu í study
     */
    public void NaestaStudy() {
        if (fxCardFlipLabel.getText().equals(nuverandiSpurning.getSvar())) {
            flipStudySpurningTilbaka("right");
        } else {
            SynaNaestaStudy();
        }

    }

    /**
     * fall sem sýnir næstu spurningu í test
     */
    public void SynaNaestaStudy() {
        i += 1;
        nuverandiSpurning = nuverandiQuiz.getQuiz().get(i);
        synaStudySpurning(nuverandiSpurning);
    }

    /**
     * fall sem nær í fyrri spuringu í study
     */
    public void FyrriStudy() {
        if (fxCardFlipLabel.getText().equals(nuverandiSpurning.getSvar())) {
            flipStudySpurningTilbaka("left");
        } else {
            SynaFyrriStudy();
        }

    }

    /**
     * fall sem sýnir fyrri spurningu í study
     */
    public void SynaFyrriStudy() {
        i -= 1;
        nuverandiSpurning = nuverandiQuiz.getQuiz().get(i);
        synaStudySpurning(nuverandiSpurning);
    }


    /**
     * fall sem sýnir fyrri browse siðu
     */
    public void FyrriBrowseSida() {
        browseCount -= 1;

        fxFjoldiSida.setText(browseCount + "/" + fjoldisida);

        fxBdiv1.setVisible(true);
        fxBdiv2.setVisible(true);
        fxBdiv3.setVisible(true);
        fxBdiv4.setVisible(true);
        fxBrowseDiv1.setText(quizlist.getQuizzes().get(browseCount * 4 - 4).getQuizName());
        fxBrowseDiv2.setText(quizlist.getQuizzes().get(browseCount * 4 - 3).getQuizName());
        fxBrowseDiv3.setText(quizlist.getQuizzes().get(browseCount * 4 - 2).getQuizName());
        fxBrowseDiv4.setText(quizlist.getQuizzes().get(browseCount * 4 - 1).getQuizName());

        if (browseCount == 1) {
            fxArrowLeft.setOnMouseClicked(null);
        }
        if (browseCount < fjoldisida) {
            fxArrowRight.setOnMouseClicked(event -> {
                NaestaBrowseSida();
            });
        }

    }


    /**
     * fall sem sýnir næstu browse siðu
     */
    public void NaestaBrowseSida() {
        browseCount += 1;
        int size = quizlist.getQuizzes().size();

        fxFjoldiSida.setText(browseCount + "/" + fjoldisida);

        if (browseCount * 4 <= size) {
            fxBdiv1.setVisible(true);
            fxBdiv2.setVisible(true);
            fxBdiv3.setVisible(true);
            fxBdiv4.setVisible(true);
            fxBrowseDiv1.setText(quizlist.getQuizzes().get(browseCount * 4 - 4).getQuizName());
            fxBrowseDiv2.setText(quizlist.getQuizzes().get(browseCount * 4 - 3).getQuizName());
            fxBrowseDiv3.setText(quizlist.getQuizzes().get(browseCount * 4 - 2).getQuizName());
            fxBrowseDiv4.setText(quizlist.getQuizzes().get(browseCount * 4 - 1).getQuizName());
        } else if (browseCount * 4 - size == 1) {
            fxBdiv1.setVisible(true);
            fxBdiv2.setVisible(true);
            fxBdiv3.setVisible(true);
            fxBrowseDiv1.setText(quizlist.getQuizzes().get(browseCount * 4 - 4).getQuizName());
            fxBrowseDiv2.setText(quizlist.getQuizzes().get(browseCount * 4 - 3).getQuizName());
            fxBrowseDiv3.setText(quizlist.getQuizzes().get(browseCount * 4 - 2).getQuizName());
            fxBdiv4.setVisible(false);
        } else if (browseCount * 4 - size == 2) {
            fxBdiv1.setVisible(true);
            fxBdiv2.setVisible(true);
            fxBrowseDiv1.setText(quizlist.getQuizzes().get(browseCount * 4 - 4).getQuizName());
            fxBrowseDiv2.setText(quizlist.getQuizzes().get(browseCount * 4 - 3).getQuizName());
            fxBdiv3.setVisible(false);
            fxBdiv4.setVisible(false);
        } else {
            fxBdiv1.setVisible(true);
            fxBrowseDiv1.setText(quizlist.getQuizzes().get(browseCount * 4 - 4).getQuizName());
            fxBdiv2.setVisible(false);
            fxBdiv3.setVisible(false);
            fxBdiv4.setVisible(false);
        }

        fxArrowLeft.setOnMouseClicked(event -> {
            FyrriBrowseSida();
        });

        if (fjoldisida == browseCount) {
            fxArrowRight.setOnMouseClicked(null);
        }


    }


    /**
     * fall sem leitar af quizum í listanum eftir leitarorði
     *
     * @param ord leitarorðið
     */
    public void leitaAfQuiz(String ord) {
        if (ord.length() == 0) {
            for (Quiz a : quizlist.getQuizzes()) {
                fxQuizzezList.getItems().add(a.getQuizName());
            }
        }
        fxQuizzezList.getItems().clear();
        ObservableList<Quiz> quizleit = quizlist.getQuizzesByName(ord);
        for (Quiz a : quizleit) {
            fxQuizzezList.getItems().add(a.getQuizName());

        }

        fxQuizzezList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String val = fxQuizzezList.getSelectionModel().getSelectedItem();
                Quiz valid = quizlist.getQuizByName(val);
                StudyonSearch(valid);
            }
        });


    }

    /**
     * fall sem sýnir svarið á spurningunni
     */
    public void SkodaSvar() {
        fxSynaSvar.setText(nuverandiSpurning.getSvar());
    }

    public void Clear() {
        fxCreateQuizName.setText("");
        fxCreateQuestion.setText("");
        fxCreateAnswer.setText("");
        fxCreateWrongAnswer1.setText("");
        fxCreateWrongAnswer2.setText("");
        fxCreateWrongAnswer3.setText("");

    }

    /**
     * fall sem staðfestir að það eigi að búa til nýtt quiz
     */
    public void Confirm() {
        String nafn = fxCreateQuizName.getText();
        String spurning = fxCreateQuestion.getText();
        String svar = fxCreateAnswer.getText();
        String rangtsvar1 = fxCreateWrongAnswer1.getText();
        String rangtsvar2 = fxCreateWrongAnswer2.getText();
        String rangtsvar3 = fxCreateWrongAnswer3.getText();

        if (nafn.equals("") || spurning.equals("") || svar.equals("") || rangtsvar1.equals("") || rangtsvar2.equals("") || rangtsvar3.equals("")) {

        } else {
            quizlist.setQuiz(nafn, spurning, svar, rangtsvar1, rangtsvar2, rangtsvar3);
            leitaAfQuiz("");
        }
        Clear();

    }

    /**
     * fall sem býr til nyspurningdialog svo hægt er að búa til nýja spurningu
     */
    public void NewQuestion() {
        NySpurningDialog d = new NySpurningDialog();
        Spurning sp = d.getSpurning();

        if (sp != null) {
            nuverandiQuiz.addSpurningNy(sp);
            initializeStudyWithQuiz(nuverandiQuiz, quizlist);

        } else {
            d.close();
        }

    }


}
