package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import vinnsla.Spurning;

import java.io.IOException;
import java.util.Optional;

public class NySpurningDialog extends Dialog<Spurning> {

    // viðmótshlutir
    @FXML
    private TextField fxNafn; // nafn notanda  element- engin controller í .fxml skrá þannig að breytur eru ekki litaðar
    @FXML
    private TextField fxSpurning; // spurning sem bæta á við
    @FXML
    private TextField fxSvar;// svar við spurningunni
    @FXML
    private TextField fxRangtSvar1;//rangur svarmöguleiki 1
    @FXML
    private TextField fxRangtSvar2;//rangur svarmöguleiki 2
    @FXML
    private TextField fxRangtSvar3;//rangur svarmöguleiki 3

    @FXML
    private ButtonType fxILagi;// í lagi button


    /**
     * Ný spurning dialog smiður
     */
    public NySpurningDialog() {
        setDialogPane(lesaSpurninguDialog());
        iLagiRegla();


        setResultConverter(b -> {                                 // b er af taginu ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                Spurning ny = new Spurning(fxSpurning.getText(), fxSvar.getText(), fxRangtSvar1.getText(), fxRangtSvar2.getText(), fxRangtSvar3.getText());
                return ny;
            } else {
                return null;
            }
        });         // endir á d.setResultConverter

    }

    /**
     * þettta les fxml skránna sem inniheldur dialoginn
     *
     * @return Dialoginum
     */
    private DialogPane lesaSpurninguDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nyspurning-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * Regla sem bindir disableproperty á ilagi takka við það að búið sé að fylla inn í alla reiti
     */
    private void iLagiRegla() {
        // fletta upp í lagi hnappnum út frá hnappategund
        Node iLagi = getDialogPane().lookupButton(fxILagi);
        iLagi.disableProperty()
                .bind(fxSpurning.textProperty().isEmpty()
                        .or(fxSvar.textProperty().isEmpty().or(fxRangtSvar1.textProperty().isEmpty().or(fxRangtSvar2.textProperty().isEmpty().or(fxRangtSvar3.textProperty().isEmpty())))));
    }

    /**
     * Birtir dialog d og skilar nafni notanda úr dialog
     *
     * @return skilar nafni notanda nema ef hætt við þá skilar hann null
     */
    public Spurning getSpurning() {
        // Dialog birtur og svarið fengið
        Optional<Spurning> utkoma = showAndWait();
        return utkoma.orElse(null);
    }
}
