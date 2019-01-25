package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import loading.LoadingProcesor;
import loading.sending.Sender;
import singletons.StockRoom;

public class MainActivityContoller {
    @FXML
    Button manualButton;

    @FXML
    Button autoButton;

    @FXML
    TextArea textArea;

    @FXML
    Label shipCounter;

    @FXML
    private Label containerCounter;

    volatile StockRoom stockRoom = StockRoom.getStockRoom();

    @FXML
    private void initialize() {
        this.loadingProcesor = new LoadingProcesor(this);
        Sender.setTextArea(textArea);
    }

    LoadingProcesor loadingProcesor;

    public MainActivityContoller() {

    }


    public void onManualButtonClick() {

        loadingProcesor.manual();
        shipCounter.setText("Ships: " + stockRoom.getShipsCount());
    }

    public void onAutoButtonClick() {
        loadingProcesor.auto();
    }

    public TextArea getTextArea() {
        return textArea;
    }


    public void updateShipCounter() {
        shipCounter.setText("Ships: " + stockRoom.getShipsCount());

    }

    public void updateContainerCounter() {
        containerCounter.setText("Containers: " + stockRoom.getContainerCount());

    }
}
