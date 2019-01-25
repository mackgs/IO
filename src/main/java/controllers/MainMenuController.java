package controllers;
import org.apache.commons.io.FilenameUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import singletons.FileStore;
import javafx.scene.control.Alert;
import java.io.File;
import java.io.IOException;

public class MainMenuController extends BorderPane {


    @FXML
    private Label closeButton;

    @FXML
    Button browseButton;

    @FXML
    private VBox dragAndDrop;

    public MainMenuController() {
        super();

    }


    private void openFile(File file) {
        if (FilenameUtils.getExtension(file.getName()).equals("txt")) {
            FileStore.setFileStore(file);
            setCenterPanel();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong file type");
            alert.showAndWait();
        }

    }


    public void onDragOver(DragEvent event) {
        if (event.getGestureSource() != dragAndDrop
                && event.getDragboard().hasFiles()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    public void onDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            openFile(db.getFiles().get(0));
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }


    public void onCloseClick() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    public void onBrowseClick() {
        final FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(browseButton.getScene().getWindow());
        if (file != null && file.exists()) {
            openFile(file);
        }
    }

    private void setCenterPanel() {
        BorderPane root = (BorderPane) browseButton.getScene().getRoot();
        FXMLLoader loader = new FXMLLoader();


        VBox vbox = null;
        try {
            vbox = (VBox) loader.load(getClass().getResource("../algorithmChose.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setCenter(vbox);
    }
}
