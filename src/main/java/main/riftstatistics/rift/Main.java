package main.riftstatistics.rift;

import com.merakianalytics.orianna.Orianna;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.riftstatistics.rift.Connections.BDDConnection;
import main.riftstatistics.rift.Controllers.NotLogWindowController;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    BDDConnection bddConnection = new BDDConnection();
    @Override
    public void start (Stage stage) throws IOException {
        Orianna.setRiotAPIKey(bddConnection.getRiotAPIKey());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/main/riftstatistics/Views/NotLogWindow-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 850);
        NotLogWindowController controller = fxmlLoader.getController();
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/main/riftstatistics/Styles/NotLogWindow.css")).toExternalForm());
        stage.setTitle("RiftStatistics");
        String imagePath = Objects.requireNonNull(getClass().getResource("/main/riftstatistics/Drawables/GeneralImages/logoRiftStatisticsStageIcon.png")).toExternalForm();
        stage.getIcons().add(new Image(imagePath));
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