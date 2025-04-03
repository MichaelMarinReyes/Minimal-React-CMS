package controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class VisorHtmlController {

    private final String inicioHtml = "";

    @FXML
    private WebView webView;

    @FXML
    public void initialize() {
        WebEngine webEngine = webView.getEngine();
        String contenidoHtml = "<html><body><h1>¡Hola, JavaFX!</h1><p>Este es un visor HTML en WebView.</p></body></html>";
        webEngine.loadContent(contenidoHtml);
    }
}