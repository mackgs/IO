import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();


        BorderPane root = (BorderPane) loader.load(getClass().getResource("main.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 450));

        addMovement(primaryStage, root);

        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();

    }

    private void addMovement(Stage primaryStage, BorderPane root) {
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }


}


