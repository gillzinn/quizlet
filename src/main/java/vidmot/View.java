package vidmot;

public enum View {


    MAIN("quizlet-view.fxml"),
    TEST("test-view.fxml"),
    STUDY("study-view.fxml"),
    BROWSE("browse-view.fxml"),
    CREATE("create-view.fxml");

    private String filename;

    //smiður fyrir skráarnafn á fxml skránni
    View(String filename) {
        this.filename = filename;
    }

    //skilar skráarnafninu
    public String getFilename() {
        return filename;
    }
}
