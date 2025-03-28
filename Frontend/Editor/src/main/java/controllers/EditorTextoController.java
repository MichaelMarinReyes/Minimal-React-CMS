package controllers;

import conexion.Cliente;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EditorTextoController {
    @FXML
    private TextArea textArea;
    @FXML
    private Label columnaLabel;
    @FXML
    private ListView<String> numeroLinea;
    private String texto = "";

    @FXML
    public void initialize() {
        textArea.setOnKeyReleased(this::actualizarNumeroColumna);
        actualizarNumeroLinea();
        textArea.textProperty().addListener((obs, oldText, newText) -> actualizarNumeroLinea());

        textArea.setStyle("-fx-font-size: 14px;");
        numeroLinea.setStyle("-fx-font-size: 12px;");
        numeroLinea.setFixedCellSize(18);
        numeroLinea.getStylesheets().add(getClass().getResource("/styles/editor-texto-style.css").toExternalForm());
        Platform.runLater(() -> {
            sincronizarScroll();
            ocultarScrollBar();
        });
    }

    private void actualizarNumeroColumna(KeyEvent event) {
        int posicionColumna = textArea.getCaretPosition();
        int ultimaLinea = textArea.getText().lastIndexOf("\n", posicionColumna - 1);
        int columna = (ultimaLinea == -1) ? posicionColumna : posicionColumna - ultimaLinea - 1;
        columnaLabel.setText(String.valueOf(columna + 1));
    }

    private void actualizarNumeroLinea() {
        String[] lineas = textArea.getText().split("\n", -1);
        numeroLinea.getItems().clear();
        for (int i = 1; i <= lineas.length; i++) {
            numeroLinea.getItems().add(String.valueOf(i));
        }
    }

    private void sincronizarScroll() {
        ScrollBar textAreaScrollBar = getVerticalScrollBar(textArea);
        ScrollBar listViewScrollBar = getVerticalScrollBar(numeroLinea);

        if (textAreaScrollBar != null && listViewScrollBar != null) {
            listViewScrollBar.valueProperty().bindBidirectional(textAreaScrollBar.valueProperty());
        }
    }

    private void ocultarScrollBar() {
        ScrollBar scrollBar = getVerticalScrollBar(numeroLinea);
        if (scrollBar != null) {
            scrollBar.setVisible(false);
            scrollBar.setManaged(false);
        }
    }

    private ScrollBar getVerticalScrollBar(Control control) {
        for (Node n : control.lookupAll(".scroll-bar")) {
            if (n instanceof ScrollBar) {
                ScrollBar scrollBar = (ScrollBar) n;
                if (scrollBar.getOrientation() == javafx.geometry.Orientation.VERTICAL) {
                    return scrollBar;
                }
            }
        }
        return null;
    }

    @FXML
    public void obtenerTexto() {
        texto = textArea.getText();  // Obtener el texto de la TextArea
        System.out.println("TEXTO DEL EDITOR: " + texto);
        new Thread(() -> {
            Cliente cliente = null;
            try {
                cliente = Cliente.getInstancia();
                System.out.println(cliente.post(texto));
            } catch (IOException e) {
                System.err.println("Error al enviar texto: " + e.getMessage());
            }
        }).start();
    }

}