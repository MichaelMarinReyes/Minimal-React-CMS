package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import utils.RutaVista;

import java.io.File;
import java.io.IOException;

public class VentanaPrincipalController {
    @FXML
    private BorderPane contenedorPrincipal;
    @FXML
    private MenuItem abrirEditorMenuItem;
    @FXML
    private MenuItem abrirChooserMenuItem;
    @FXML
    private MenuItem webViewMenuItem;
    @FXML
    private HBox chooser = new HBox();
    @FXML
    private MenuBar menuBar;

    @FXML
    public void initialize() {
        abrirEditorMenuItem.setOnAction(event -> cargarVista(RutaVista.EDITOR_TEXTO.getPath()));
        abrirChooserMenuItem.setOnAction(event -> abrirChooser());
        webViewMenuItem.setOnAction(event -> cargarVista(RutaVista.WEB_VIEW.getPath()));

        //REDIMENSIONAMIENTO DE CONTENEDOR
        contenedorPrincipal.widthProperty().addListener((observable, oldValue, newValue) -> {
            contenedorPrincipal.setMaxWidth(newValue.doubleValue());
        });

        contenedorPrincipal.heightProperty().addListener((observable, oldValue, newValue) -> {
            contenedorPrincipal.setMaxHeight(newValue.doubleValue());
        });
        //cargarVista(RutaVista.PESTAÑAS_EDITOR.getPath());
        cargarVista(RutaVista.EDITOR_TEXTO.getPath());
    }

    public void cargarVista(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            AnchorPane pane = (AnchorPane) loader.load();

            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(pane);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
        } catch (IOException e) {
            System.err.println("Error al cargar vista: " + e.getMessage());
        }
    }

    private void abrirChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos MTSX", "*.mtsx"));
        File file = fileChooser.showOpenDialog(null);

    }
}