package main.riftstatistics.rift.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoggedWindowController implements Initializable {
    String pathImages = Objects.requireNonNull(NotLogWindowController.class.getResource("/main/riftstatistics/Drawables/GeneralImages")).toExternalForm();
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

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
    private Region areaClicks3;
    @FXML
    private Region areaClicks2;
    @FXML
    private Region areaClicks1;
    @FXML
    private ImageView imageCerrar;
    @FXML
    private ImageView imageAgrandar;
    @FXML
    private ImageView imageMinimizar;
    @FXML
    private Region areaClicks4;
    @FXML
    private BorderPane borderPanePrincipalCentro;
    @FXML
    private GridPane gridPaneMensajeCuenta;
    @FXML
    private RowConstraints gridPaneMensajeCuentaCelda1;
    @FXML
    private HBox hBoxMensajeCuenta;
    @FXML
    private Label labelMensajeCuenta;


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

    public void setStage (Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void sideMenuOpenOnAction(Event event) {
        List<Image> images = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            images.add(new Image(pathImages + "/LogoAnimation/ImagenLogoRiftStatistics" + i + ".png"));
        }

        // Creamos una nueva línea de tiempo
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), eventT -> {
                    // Cambiamos la imagen del ImageView
                    imageLogo.setImage(images.get(0));
                    // Movemos la imagen al final de la lista
                    images.add(images.remove(0));
                })
        );

        timeline.setCycleCount(7);
        timeline.play();
    }
}
