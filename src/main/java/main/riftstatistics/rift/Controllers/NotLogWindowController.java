package main.riftstatistics.rift.Controllers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.riftstatistics.rift.BDDConnection.BDDConnection;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class NotLogWindowController implements Initializable {
    String pathImages = Objects.requireNonNull(NotLogWindowController.class.getResource("/main/riftstatistics/Drawables/GeneralImages")).toExternalForm();
    private double xOffset = 0;
    private double yOffset = 0;
    private Stage stage;
    @FXML
    private Label labelTitulo;
    @FXML
    private GridPane gridPrincipalFondo;
    @FXML
    private Button botonIniciarSesion;
    @FXML
    private Label labelDontLogin;
    @FXML
    private Label labelEnterGuest;
    @FXML
    private ImageView imageCerrar;
    @FXML
    private ImageView imageAgrandar;
    @FXML
    private ImageView imageMinimizar;
    @FXML
    private GridPane gridPrincipalMenu;
    @FXML
    private GridPane gridPanePrincipalBarraBuscador;
    @FXML
    private ImageView imageLogo;
    @FXML
    private ImageView imageAvanzar;
    @FXML
    private ImageView imageRecargar;
    @FXML
    private ImageView imageRetroceder;
    @FXML
    private TextField textFieldBuscador;
    @FXML
    private Region areaClicks1;
    @FXML
    private Region areaClicks2;
    @FXML
    private Region areaClicks3;
    @FXML
    private Region areaClicks4;
    private FadeTransition fadeOut;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        inicializacionPrincipalVentana();
    }

    private void inicializacionPrincipalVentana () {
        ArrayList<Region> regiones = new ArrayList<>();
        regiones.add(areaClicks1);
        regiones.add(areaClicks2);
        regiones.add(areaClicks3);
        regiones.add(areaClicks4);

        for (Region region: regiones) {
            region.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            region.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });
        }

        ArrayList<ImageView> imagenes = new ArrayList<>();
        imagenes.add(imageCerrar);
        imagenes.add(imageAgrandar);
        imagenes.add(imageMinimizar);

        for (ImageView imageView: imagenes) {
            imageView.setOnMouseEntered(event -> imageView.setImage(new Image(pathImages + imageView.getId() + "Selected" + ".png")));
            imageView.setOnMouseExited(event -> imageView.setImage(new Image(pathImages + imageView.getId() + ".png")));
        }
    }

    @FXML
    public void imageViewCloseOnAction() {
        System.exit(0);
    }

    @FXML
    public void imageViewMinimizeOnAction() {
        stage.setIconified(true);
    }

    @FXML
    public void imageViewMaxiOnAction(Event event) {
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    @FXML
    public void iniciarSesionButtonOnAction (ActionEvent actionEvent) {
        Pane glassPane = new Pane();
        glassPane.setStyle("-fx-background-color: rgba(54,58,56,1);"); // color semi-transparente
        glassPane.setOpacity(0.85); // inicialmente transparente

// Obtener el BorderPane raíz de la escena de la ventana principal y agregar el glassPane en la capa superior
        BorderPane root = (BorderPane) stage.getScene().getRoot();
        GridPane gridPane = (GridPane) root.getCenter();
        root.setCenter(glassPane);
        glassPane.setPrefSize(root.getWidth(), root.getHeight());
        BorderPane.setAlignment(glassPane, Pos.CENTER);
        glassPane.setPrefSize(root.getWidth(), root.getHeight());

// Configurar una transición para mostrar y ocultar el Pane
        Duration duration = Duration.millis(250); // duración de la transición (en milisegundos)
        FadeTransition fadeIn = new FadeTransition(duration, glassPane);
        fadeIn.setFromValue(0.85);
        fadeIn.setToValue(0.95); // opacidad final (totalmente opaco)

        FadeTransition gridPaneFadeIn = new FadeTransition(duration, gridPane);
        gridPaneFadeIn.setFromValue(0.95);
        gridPaneFadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(duration, glassPane);
        fadeOut.setDuration(Duration.millis(250));
        fadeOut.setToValue(0.95); // opacidad final (totalmente transparente)

        fadeOut.setOnFinished(event -> {
            root.setCenter(gridPane);
            gridPaneFadeIn.play();
        });

        this.fadeOut = fadeOut;

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
        tempStage.initOwner(this.stage);
        controller.setControllerParent(this);
        tempStage.initStyle(StageStyle.UNDECORATED);
        tempStage.setScene(scene);
        tempStage.setX(stage.getX() + (stage.getWidth() - scene.getWidth()) / 2);
        tempStage.setY(stage.getY() + (stage.getHeight() - scene.getHeight()) / 2);
        tempStage.initModality(Modality.APPLICATION_MODAL);
        fadeIn.play();
        tempStage.show();
    }

    public void doFadeOut() {
        fadeOut.play();
    }
    public void closeStage() {
        stage.close();
    }

    public void setStage (Stage stage) {
        this.stage = stage;
    }
}