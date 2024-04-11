package vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import vinnsla.Quiz;
import vinnsla.QuizList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewSwitcher {

    private static Scene scene;//núverandi scene
    private static Map<View, Parent> cache = new HashMap<>();//hashmap sem geymir uppl um scenes sem gerðust á undan

    /**
     * fall sem setur scene
     *
     * @param scene er scene sem á að setja
     */
    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }


    /**
     * Fall sem skiptir um scenu
     *
     * @param view er síðan sem á að breyta í
     */
    public static void switchTo(View view) {
        if (scene == null) {
            return;
        }
        try {
            Parent root;

            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFilename()));
            root = loader.load();
            cache.put(view, root);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Fall sem skiptir um scenu
     *
     * @param view     er síðan sem á að breyta í
     * @param quizList er listi af quizum sem er notaður til að uppfæra listann á nýju síðunni
     */
    public static void switchTo(View view, QuizList quizList) {

        if (scene == null) {
            return;
        }
        try {
            Parent root;


            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFilename()));
            root = loader.load();
            QuizLetController browseController = loader.getController();
            switch (view.getFilename()) {
                case "browse-view.fxml":
                    browseController.initializeBrowse(quizList);

                    break;
                case "study-view.fxml":
                    browseController.initializeStudy(quizList);

                    break;
                case "test-view.fxml":
                    browseController.initializeTest(quizList);

                    break;
                case "create-view.fxml":
                    browseController.initializeCreate(quizList);
            }

            cache.put(view, root);


            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Fall sem skiptir um scenu
     *
     * @param view     er síðan sem á að breyta í
     * @param quizList er listi af quizum sem er notaður til að uppfæra listann á nýju síðunni
     * @param quiz     er quizið sem á að loada á nýju síðunni, til dæmis til að læra það í study
     */
    public static void switchTo(View view, Quiz quiz, QuizList quizList) {
        if (scene == null) {
            return;
        }
        try {
            Parent root;

            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFilename()));
            root = loader.load();
            if (view.getFilename().equals("study-view.fxml")) {
                QuizLetController StudyController = loader.getController();
                StudyController.initializeStudyWithQuiz(quiz, quizList);
            } else {
                QuizLetController TestController = loader.getController();
                TestController.initializeTestWithQuiz(quiz, quizList);
            }

            cache.put(view, root);


            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
