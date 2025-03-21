package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import utils.RutaVista;

import java.io.File;
import java.io.IOException;

public class VentanaPrincipalController {
    @FXML
    private AnchorPane contenedorPrincipal;
    @FXML
    private MenuItem abrirEditorMenuItem;
    @FXML
    private MenuItem abrirChooserMenuItem;
    @FXML
    private HBox chooser = new HBox();

    @FXML
    public void initialize() {
        //cargarVista(RutaVista.PESTAÑAS_EDITOR.getPath());
        abrirEditorMenuItem.setOnAction(event -> cargarVista(RutaVista.EDITOR_TEXTO.getPath()));
        abrirChooserMenuItem.setOnAction(event -> abrirChooser());

        cargarVista(RutaVista.EDITOR_TEXTO.getPath());
    }

    public void cargarVista(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Node vista = loader.load();
            contenedorPrincipal.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
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