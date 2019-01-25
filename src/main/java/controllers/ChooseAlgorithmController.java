package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import loading.LoadingProcesor;
import loading.ShipLoaderCustom;
import loading.ShipLoaderGreedy;

import java.io.IOException;

public class ChooseAlgorithmController extends VBox {

    @FXML
    private Button greedyForceAlgorithmButton;


    public void onGreedyAlgorithmButtonClick() {


        BorderPane root = (BorderPane) greedyForceAlgorithmButton.getScene().getRoot();
        FXMLLoader loader = new FXMLLoader();


        BorderPane borderPane = null;
        try {
            borderPane = (BorderPane) loader.load(getClass().getResource("../mainActivity.fxml"));
            LoadingProcesor.shipLoader = new ShipLoaderGreedy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setCenter(borderPane);
    }

    public void onCustomAlgorithmButtonClick() {


        BorderPane root = (BorderPane) greedyForceAlgorithmButton.getScene().getRoot();
        FXMLLoader loader = new FXMLLoader();


        BorderPane borderPane = null;
        try {
            borderPane = (BorderPane) loader.load(getClass().getResource("../mainActivity.fxml"));
            LoadingProcesor.shipLoader = new ShipLoaderCustom();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setCenter(borderPane);
    }


}
