package nsu.fit.oop.boryapatrushev.task_2_3_1.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Main class of Snake game
 * Used to start the game and load the FXML document("snake.fxml")
 */
public final class Main extends Application {

    public static void main(final String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(final Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("snake.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
