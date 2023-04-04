package main.riftstatistics.rift;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.riftstatistics.rift.Controllers.NotLogWindowController;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/main/riftstatistics/Views/NotLogWindow-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 850);
        NotLogWindowController controller = fxmlLoader.getController();
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/main/riftstatistics/Styles/NotLogWindow.css")).toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMinHeight(850);
        stage.setMinWidth(1000);
        controller.setStage(stage);
        stage.show();
    }

    public static void main (String[] args) {
        launch();
    }
}