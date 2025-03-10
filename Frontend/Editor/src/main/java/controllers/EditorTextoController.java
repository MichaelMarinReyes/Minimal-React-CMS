package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class EditorTextoController {
    @FXML
    private TextArea textArea;
    @FXML
    private Label columnaLabel;
    @FXML
    private ListView<Integer> lineNumbers;

    @FXML
    public void initialize() {
        textArea.setOnKeyReleased(this::actualizarPosicionCursor);
        actualizarNumerosDeLinea();
        textArea.textProperty().addListener((obs, oldText, newText) -> actualizarNumerosDeLinea());

        Platform.runLater(() -> {
            sincronizarScroll();
            ocultarScrollBar();
        });
    }

    private void actualizarPosicionCursor(KeyEvent event) {
        int caretPosition = textArea.getCaretPosition();
        int lastNewLine = textArea.getText().lastIndexOf("\n", caretPosition - 1);
        int columna = (lastNewLine == -1) ? caretPosition : caretPosition - lastNewLine - 1;
        columnaLabel.setText(String.valueOf(columna));
    }

    private void actualizarNumerosDeLinea() {
        String[] lineas = textArea.getText().split("\n", -1);
        lineNumbers.getItems().clear();
        for (int i = 1; i <= lineas.length; i++) {
            lineNumbers.getItems().add(i);
        }
    }

    private void sincronizarScroll() {
        ScrollBar textAreaScrollBar = getVerticalScrollBar(textArea);
        ScrollBar listViewScrollBar = getVerticalScrollBar(lineNumbers);

        if (textAreaScrollBar != null && listViewScrollBar != null) {
            listViewScrollBar.valueProperty().bindBidirectional(textAreaScrollBar.valueProperty());
        }
    }

    private void ocultarScrollBar() {
        ScrollBar scrollBar = getVerticalScrollBar(lineNumbers);
        if (scrollBar != null) {
            scrollBar.setVisible(false);
            scrollBar.setManaged(false); // Evita que JavaFX reserve espacio para el scroll
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
}