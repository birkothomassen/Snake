package prosjekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class snakeApp extends Application{
public static void main(String[] args) {
    Application.launch(args);
}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("snake.fxml"))));
        primaryStage.show();
    }
    
}
