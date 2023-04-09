package main.riftstatistics.rift.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.riftstatistics.rift.ResizeHelper;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class LoggedWindowController implements Initializable {
    String pathImages = Objects.requireNonNull(NotLogWindowController.class.getResource("/main/riftstatistics/Drawables/GeneralImages")).toExternalForm();
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean menuVisible;

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
    @FXML
    private Label labelNombreCuenta;
    @FXML
    private Label labelCuentaHome;
    @FXML
    private Label labelCampeones;
    @FXML
    private Label labelEstadisticas;
    @FXML
    private Label labelNotasParche;
    @FXML
    private GridPane gridPaneMenuImages;
    @FXML
    private GridPane gridPaneMenulabels;
    @FXML
    private Pane gridPaneMenuFondo1;
    @FXML
    private Pane gridPaneMenuFondo2;
    @FXML
    private Pane gridPaneMenuFondo3;
    @FXML
    private Pane gridPaneMenuFondo4;
    @FXML
    private Label labelRiftStatistics;
    @FXML
    private ImageView imageCuenta;
    @FXML
    private Pane paneImageCuenta;
    @FXML
    private ImageView imageCuentaHome;
    @FXML
    private Pane paneimageCuentaHome;
    @FXML
    private ImageView imageCampeones;
    @FXML
    private Pane paneImageCampeones;
    @FXML
    private ImageView imageEstadisticas;
    @FXML
    private Pane paneImageEstadisticas;
    @FXML
    private ImageView imageNotasParche;
    @FXML
    private Pane paneImageNotasParche;
    @FXML
    private Pane paneImageCuentaBehind;
    @FXML
    private Pane paneimageCuentaHomeBehind;
    @FXML
    private Pane paneImageCampeonesBehind;
    @FXML
    private Pane paneImageEstadisticasBehind;
    @FXML
    private Pane paneImageNotasParcheBehind;
    @FXML
    private Pane paneLabelNombreCuentaBehind;
    @FXML
    private Pane paneLabelNombreCuenta;
    @FXML
    private Pane paneLabelCuentaHomeBehind;
    @FXML
    private Pane paneLabelCuentaHome;
    @FXML
    private Pane paneLabelCampeonesBehind;
    @FXML
    private Pane paneLabelCampeones;
    @FXML
    private Pane paneLabelEstadisticasBehind;
    @FXML
    private Pane paneLabelEstadisticas;
    @FXML
    private Pane paneLabelNotasParcheBehind;
    @FXML
    private Pane paneLabelNotasParche;


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

            region.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    imageViewMaxiOnAction(event);
                }
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

        gridPaneMenulabels.setVisible(false);
        gridPaneMenuFondo2.setVisible(false);
        gridPaneMenuFondo4.setVisible(false);

        List<Image> images = new ArrayList<>();
        images.add(new Image(pathImages + "/ImagenHomeUnselected" + ".png"));
        images.add(new Image(pathImages + "/ImagenHomeSelected" + ".png"));
        images.add(new Image(pathImages + "/ImagenChampionsUnselected" + ".png"));
        images.add(new Image(pathImages + "/ImagenChampionsSelected" + ".png"));
        images.add(new Image(pathImages + "/ImagenTierlistUnselected" + ".png"));
        images.add(new Image(pathImages + "/ImagenTierlistSelected" + ".png"));
        images.add(new Image(pathImages + "/ImagenPatchnotesUnselected" + ".png"));
        images.add(new Image(pathImages + "/ImagenPatchnotesSelected" + ".png"));

        ArrayList<Pane> panes1 = new ArrayList<>();
        panes1.add(paneImageCuenta);
        panes1.add(paneimageCuentaHome);
        panes1.add(paneImageCampeones);
        panes1.add(paneImageEstadisticas);
        panes1.add(paneImageNotasParche);
        ArrayList<Pane> panes2 = new ArrayList<>();
        panes2.add(paneLabelNombreCuenta);
        panes2.add(paneLabelCuentaHome);
        panes2.add(paneLabelCampeones);
        panes2.add(paneLabelEstadisticas);
        panes2.add(paneLabelNotasParche);
        ArrayList<Pane> behindPanes1 = new ArrayList<>();
        behindPanes1.add(paneImageCuentaBehind);
        behindPanes1.add(paneimageCuentaHomeBehind);
        behindPanes1.add(paneImageCampeonesBehind);
        behindPanes1.add(paneImageEstadisticasBehind);
        behindPanes1.add(paneImageNotasParcheBehind);
        ArrayList<Pane> behindPanes2 = new ArrayList<>();
        behindPanes2.add(paneLabelNombreCuentaBehind);
        behindPanes2.add(paneLabelCuentaHomeBehind);
        behindPanes2.add(paneLabelCampeonesBehind);
        behindPanes2.add(paneLabelEstadisticasBehind);
        behindPanes2.add(paneLabelNotasParcheBehind);

        for (int i = 0; i < panes1.size(); i++) {
            Pane pane1= panes1.get(i);
            Pane pane2 = panes2.get(i);
            Pane behindPane = behindPanes1.get(i);
            Pane behindPane2 = behindPanes2.get(i);
            int finalI = i;

            pane1.setOnMouseEntered(event -> {
                behindPane.setStyle("-fx-background-color: #C0C2CB;");
                behindPane2.setStyle("-fx-background-color: #C0C2CB;");
                if (finalI ==1) {
                    imageCuentaHome.setImage(images.get(1));
                } else if (finalI == 2) {
                    imageCampeones.setImage(images.get(3));
                } else if (finalI == 3) {
                    imageEstadisticas.setImage(images.get(5));
                } else if (finalI == 4) {
                    imageNotasParche.setImage(images.get(7));
                }
            });
            pane1.setOnMouseExited(event -> {
                behindPane.setStyle("");
                behindPane2.setStyle("");
                if (finalI ==1) {
                    imageCuentaHome.setImage(images.get(0));
                } else if (finalI == 2) {
                    imageCampeones.setImage(images.get(2));
                } else if (finalI == 3) {
                    imageEstadisticas.setImage(images.get(4));
                } else if (finalI == 4) {
                    imageNotasParche.setImage(images.get(6));
                }
            });
            pane2.setOnMouseEntered(event -> {
                behindPane.setStyle("-fx-background-color: #C0C2CB;");
                behindPane2.setStyle("-fx-background-color: #C0C2CB;");
                if (finalI ==1) {
                    imageCuentaHome.setImage(images.get(1));
                } else if (finalI == 2) {
                    imageCampeones.setImage(images.get(3));
                } else if (finalI == 3) {
                    imageEstadisticas.setImage(images.get(5));
                } else if (finalI == 4) {
                    imageNotasParche.setImage(images.get(7));
                }
            });
            pane2.setOnMouseExited(event -> {
                behindPane.setStyle("");
                behindPane2.setStyle("");
                if (finalI ==1) {
                    imageCuentaHome.setImage(images.get(0));
                } else if (finalI == 2) {
                    imageCampeones.setImage(images.get(2));
                } else if (finalI == 3) {
                    imageEstadisticas.setImage(images.get(4));
                } else if (finalI == 4) {
                    imageNotasParche.setImage(images.get(6));
                }
            });
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
    public void menuGridImageMouseEntered (Event event) {
        openSideMenu();
    }

    @FXML
    public void menuFondo3MouseEntered (Event event) {
        openSideMenu();
    }

    @FXML
    public void menuFondo1MouseEntered (Event event) {
        openSideMenu();
    }

    @FXML
    public void gridPrincipalMouseExited (Event event) {
        closeSideMenu();
    }

    @FXML
    public void buscarSummonerOnAction (Event event) {
    }

    private void openSideMenu() {
        if (!menuVisible) {
            List<Image> images = new ArrayList<>();
            for (int i = 1; i < 8; i++) {
                images.add(new Image(pathImages + "/LogoAnimation/ImagenLogoRiftStatistics" + i + ".png"));
            }

            Timeline openMenuImage = new Timeline(
                    new KeyFrame(Duration.seconds(0.06), eventT -> {
                        imageLogo.setImage(images.get(0));
                        images.add(images.remove(0));
                    })
            );
            openMenuImage.setCycleCount(7);

            double fromOffset = 0 - gridPaneMenulabels.getWidth();

            TranslateTransition openMenu = new TranslateTransition(Duration.millis(500), gridPaneMenuFondo4);
            openMenu.setFromX(fromOffset);
            openMenu.setToX(0);
            TranslateTransition openMenu2 = new TranslateTransition(Duration.millis(500), gridPaneMenuFondo2);
            openMenu2.setFromX(fromOffset);
            openMenu2.setToX(0);
            TranslateTransition openMenu3 = new TranslateTransition(Duration.millis(500), gridPaneMenulabels);
            openMenu3.setFromX(fromOffset);
            openMenu3.setToX(0);

            gridPaneMenuFondo4.setVisible(true);
            gridPaneMenuFondo2.setVisible(true);
            gridPaneMenulabels.setVisible(true);

            openMenu.play();
            openMenu2.play();
            openMenu3.play();
            openMenuImage.play();

            menuVisible = true;
        }
    }

    private void closeSideMenu() {
        if (menuVisible) {
            List<Image> images = new ArrayList<>();
            for (int i = 1; i < 8; i++) {
                images.add(new Image(pathImages + "/LogoAnimation/ImagenLogoRiftStatistics" + i + ".png"));
            }

            Timeline closeMenuImage = new Timeline(
                    new KeyFrame(Duration.seconds(0.06), eventT -> {
                        imageLogo.setImage(images.get(images.size() - 1));
                        images.add(0, images.remove(images.size() - 1));
                    })
            );
            closeMenuImage.setCycleCount(7);

            double fromOffset = 0 - gridPaneMenulabels.getWidth();

            TranslateTransition closeMenu = new TranslateTransition(Duration.millis(500), gridPaneMenuFondo4);
            closeMenu.setToX(fromOffset);
            TranslateTransition closeMenu2 = new TranslateTransition(Duration.millis(500), gridPaneMenuFondo2);
            closeMenu2.setToX(fromOffset);
            TranslateTransition closeMenu3 = new TranslateTransition(Duration.millis(500), gridPaneMenulabels);
            closeMenu3.setToX(fromOffset);

            closeMenu.play();
            closeMenu2.play();
            closeMenu3.play();
            closeMenuImage.play();

            closeMenu.setOnFinished(e -> {
                gridPaneMenuFondo4.setVisible(false);
                gridPaneMenuFondo2.setVisible(false);
                gridPaneMenulabels.setVisible(false);
            });

            menuVisible = false;
        }
    }
}
