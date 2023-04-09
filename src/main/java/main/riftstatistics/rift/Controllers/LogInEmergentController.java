package main.riftstatistics.rift.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.riftstatistics.rift.Connections.BDDConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import main.riftstatistics.rift.ResizeHelper;
import org.apache.commons.codec.digest.DigestUtils;

public class LogInEmergentController implements Initializable {
    String pathImages = Objects.requireNonNull(NotLogWindowController.class.getResource("/main/riftstatistics/Drawables/GeneralImages")).toExternalForm();
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
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelPassword;
    @FXML
    private TextField textAreaUsername;
    @FXML
    private TextField textAreaPassword;
    @FXML
    private Label labelIncorrectPassword;
    private NotLogWindowController notLogWindowController;

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
        this.notLogWindowController.doFadeOut();
    }

    @FXML
    public void iniciarSesionButtonOnAction (ActionEvent actionEvent) {
        String username = textAreaUsername.getText();
        String password = textAreaPassword.getText();
        String hashedPassword = DigestUtils.sha256Hex(password);

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        BDDConnection conexion = BDDConnection.getInstance();
        Connection connection = conexion.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query); {

                statement.setString(1, username);
                statement.setString(2, hashedPassword);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    labelIncorrectPassword.setVisible(false);
                    Stage stage = (Stage) botonLogIn.getScene().getWindow();
                    stage.close();
                    this.notLogWindowController.closeStage();
                    this.notLogWindowController.doFadeOut();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/riftstatistics/Views/LoggedWindow-view.fxml"));
                    Scene scene;
                    try {
                        scene = new Scene(fxmlLoader.load(), 1180, 800);
                    }catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    LoggedWindowController controller = fxmlLoader.getController();
                    Stage tempStage = new Stage();
                    tempStage.initStyle(StageStyle.UNDECORATED);
                    tempStage.setScene(scene);
                    controller.setStage(tempStage);
                    tempStage.setX(stage.getX() + (stage.getWidth() - scene.getWidth()) / 2);
                    tempStage.setY(stage.getY() + (stage.getHeight() - scene.getHeight()) / 2);
                    stage.close();
                    ResizeHelper.addResizeListener(tempStage);
                    tempStage.setMinWidth(1180);
                    tempStage.setMinHeight(800);
                    tempStage.show();
                } else {
                    labelIncorrectPassword.setVisible(true);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void CambiarASignUpOnAction (Event event) {
        Stage stage = (Stage) labelSignIn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/riftstatistics/Views/SignUpEmergent-view.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 711, 476);
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }
        SignUpEmergentController controller = fxmlLoader.getController();
        Stage tempStage = new Stage();
        tempStage.initStyle(StageStyle.UNDECORATED);
        tempStage.setScene(scene);
        controller.setControllerParent(this.notLogWindowController);
        tempStage.setX(stage.getX() + (stage.getWidth() - scene.getWidth()) / 2);
        tempStage.setY(stage.getY() + (stage.getHeight() - scene.getHeight()) / 2);
        tempStage.initModality(Modality.APPLICATION_MODAL);
        stage.close();
        tempStage.show();
    }

    public void setControllerParent (NotLogWindowController notLogWindowController) {
        this.notLogWindowController = notLogWindowController;
    }
}
