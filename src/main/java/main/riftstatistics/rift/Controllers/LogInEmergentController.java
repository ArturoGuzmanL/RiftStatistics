package main.riftstatistics.rift.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogInEmergentController implements Initializable {
    String pathImages = Objects.requireNonNull(NotLogWindowController.class.getResource("/main/riftstatistics/Drawables/")).toExternalForm();
    @FXML
    private AnchorPane anchorPanePrincipal;
    @FXML
    private BorderPane borderPanePrincipal;
    @FXML
    private GridPane gridPanePrincipalTop;
    @FXML
    private GridPane vel;
    @FXML
    private GridPane gridPanePrincipalCenter;
    @FXML
    private ImageView imageCerrarEmergente;
    @FXML
    private Label labelDontHaveAcc;
    @FXML
    private Label labelSignIn;
    @FXML
    private Button botonLogIn;

    public void initialize (URL url, ResourceBundle resourceBundle) {
        inicializacionPrincipalVentana();
    }

    private void inicializacionPrincipalVentana () {
        imageCerrarEmergente.setOnMouseEntered(event -> imageCerrarEmergente.setImage(new Image(pathImages + imageCerrarEmergente.getId() + "Selected" + ".png")));
        imageCerrarEmergente.setOnMouseExited(event -> imageCerrarEmergente.setImage(new Image(pathImages + imageCerrarEmergente.getId() + ".png")));
    }

    @FXML
    public void imageViewCloseOnAction() {
        Stage stage = (Stage) imageCerrarEmergente.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void iniciarSesionButtonOnAction (ActionEvent actionEvent) {
    }
}
