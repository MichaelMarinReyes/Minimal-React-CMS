package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import utils.RutaVista;

import java.io.IOException;

public class PestañasEditorController {
    @FXML
    TabPane tabPane;
    @FXML
    private void initialize() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RutaVista.EDITOR_TEXTO.getPath()));
        try {
            Node editor = fxmlLoader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
