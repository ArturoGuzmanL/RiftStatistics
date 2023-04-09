package main.riftstatistics.rift.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.riftstatistics.rift.Connections.BDDConnection;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpEmergentController implements Initializable {
    String pathImages = Objects.requireNonNull(NotLogWindowController.class.getResource("/main/riftstatistics/Drawables/GeneralImages")).toExternalForm();

    @FXML
    private AnchorPane anchorPanePrincipal;
    @FXML
    private BorderPane borderPanePrincipal;
    @FXML
    private GridPane gridPanePrincipalTop;
    @FXML
    private ImageView imageCerrarEmergente;
    @FXML
    private GridPane vel;
    @FXML
    private Label labelDontHaveAcc;
    @FXML
    private Label labelSignIn;
    @FXML
    private GridPane gridPanePrincipalCenter;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelPassword;
    @FXML
    private TextField textAreaUsername;
    @FXML
    private TextField textAreaPassword;
    @FXML
    private TextField textAreaEmail;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelIncorrectPassword;
    @FXML
    private Button botonSigIn;
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
    public void CrearCuentaButtonOnAction (ActionEvent actionEvent) {
        String username = textAreaUsername.getText();
        String password = textAreaPassword.getText();
        String email = textAreaEmail.getText();
        String hashedPassword = DigestUtils.sha256Hex(password);

        String error = validateInput(username, password, email);

        if (error.isEmpty()) {
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

            BDDConnection conexion = BDDConnection.getInstance();
            Connection connection = conexion.getConnection();

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                {
                    statement.setString(1, username);
                    statement.setString(2, hashedPassword);
                    statement.setString(3, email);
                    statement.executeUpdate();

                    labelIncorrectPassword.setText("Account created successfully");
                    labelIncorrectPassword.setVisible(true);
                }
            } catch (SQLException e) {
                labelIncorrectPassword.setVisible(true);
                throw new RuntimeException(e);
            }
        } else {
            labelIncorrectPassword.setText(error);
            labelIncorrectPassword.setVisible(true);
        }
    }

    @FXML
    public void cambiarALogInOnAction (Event event) {
        Stage stage = (Stage) labelSignIn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/riftstatistics/Views/LogInEmergent-view.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 711, 476);
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }
        LogInEmergentController controller = fxmlLoader.getController();
        Stage tempStage = new Stage();
        tempStage.initStyle(StageStyle.UNDECORATED);
        controller.setControllerParent(this.notLogWindowController);
        tempStage.setScene(scene);
        tempStage.setX(stage.getX() + (stage.getWidth() - scene.getWidth()) / 2);
        tempStage.setY(stage.getY() + (stage.getHeight() - scene.getHeight()) / 2);
        tempStage.initModality(Modality.APPLICATION_MODAL);
        stage.close();
        tempStage.show();
    }

    public void setControllerParent (NotLogWindowController notLogWindowController) {
        this.notLogWindowController = notLogWindowController;
    }

    private String validateInput(String username, String password, String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();

        if (isWithinLengthLimits(username, 4, 20)) {
            return "Username must be between 4 and 20 characters";
        }
        if (isWithinLengthLimits(password, 8, 20)) {
            return "Password must be between 8 and 20 characters";
        }
        if (isWithinLengthLimits(email, 1, 50)) {
            return "Email cannot have more than 50 characters";
        }
        if (!emailValidator.isValid(email)) {
            return "Invalid email address";
        }
        if (containsInvalidCharacters(username)) {
            return "Username cannot contain spaces, single quotes, or double quotes";
        }
        if (containsInvalidCharacters(password)) {
            return "Password cannot contain spaces, single quotes, or double quotes";
        }
        if (containsInvalidCharacters(email)) {
            return "Email cannot contain spaces, single quotes, or double quotes";
        }

        return "";
    }

    private boolean isWithinLengthLimits(String string, int minLength, int maxLength) {
        return string.length() < minLength || string.length() > maxLength;
    }

    private boolean containsInvalidCharacters(String string) {
        return string.matches(".*[\\s'\"].*");
    }
}
