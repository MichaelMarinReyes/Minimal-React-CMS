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
    private HBox chooser = new HBox();
    @FXML
    private MenuBar menuBar;

    @FXML
    public void initialize() {
        abrirEditorMenuItem.setOnAction(event -> cargarVista(RutaVista.EDITOR_TEXTO.getPath()));
        abrirChooserMenuItem.setOnAction(event -> abrirChooser());

        //REDIMENSIONAMIENTO DE CONTENEDOR
        contenedorPrincipal.widthProperty().addListener((observable, oldValue, newValue) -> {
            contenedorPrincipal.setMaxWidth(newValue.doubleValue());
        });

        contenedorPrincipal.heightProperty().addListener((observable, oldValue, newValue) -> {
            contenedorPrincipal.setMaxHeight(newValue.doubleValue());
        });
        //cargarVista(RutaVista.PESTAÑAS_EDITOR.getPath());
        menuBar.setBackground(new Background(new BackgroundFill(Color.web("#109ed3"), CornerRadii.EMPTY, Insets.EMPTY)));
        cargarVista(RutaVista.EDITOR_TEXTO.getPath());
    }

    public void cargarVista(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Node vista = loader.load();
            contenedorPrincipal.setPrefSize(menuBar.getPrefWidth(), menuBar.getPrefHeight());
            contenedorPrincipal.setCenter(vista);
            //contenedorPrincipal.setMaxWidth(width);
            //contenedorPrincipal.setMaxHeight(height);
            //contenedorPrincipal.setPrefSize(contenedorPrincipal.getWidth(), contenedorPrincipal.getHeight());
            //contenedorPrincipal.getChildren().setAll(vista);
        } catch (IOException e) {
            System.err.println("Error al cargar vista: " + e.getMessage());
        }
    }

    private void abrirChooser() {
        Scene scene = new Scene(chooser, 300, 300);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos MTSX", "*.mtsx"));
        File file = fileChooser.showOpenDialog(null);

        contenedorPrincipal.getChildren().setAll(chooser);
    }
}