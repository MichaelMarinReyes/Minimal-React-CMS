package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VentanaPrincipalController {
    @FXML
    private Pane contenedorPrincipal;
    @FXML
    private MenuItem abrirEditorMenuItem;

    @FXML
    public void initialize() {
        abrirEditorMenuItem.setOnAction(event -> cargarEditorTexto());
    }

    private void cargarEditorTexto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/principal/editor-texto.fxml"));
            Node editor = loader.load();

            contenedorPrincipal.getChildren().setAll(editor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}