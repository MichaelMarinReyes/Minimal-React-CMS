package controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class VisorHtmlController {

    private final String inicioHtml = "<html><body>";
    private final String fimHtml = "</body></html>";
    private String htmlUsuario;
    private String htmlFinal;

    @FXML
    private WebView webView;

    public String getHtmlUsuario() {
        return htmlUsuario;
    }

    public void setHtmlUsuario(String htmlUsuario) {
        this.htmlUsuario = htmlUsuario;
    }

    @FXML
    public void initialize() {
        WebEngine webEngine = webView.getEngine();
        String contenidoHtml = inicioHtml + "<h1>Prueba de WebView</h1><p>Prueba de párrafo</p>" + fimHtml;
        webEngine.loadContent(contenidoHtml);
    }

    public void convertirHtml(String html) {
        if (html != null) {
            htmlFinal = inicioHtml + html + fimHtml;
        }
    }
}