package utils;

public enum RutaVista {
    VENTANA("/principal/ventana-principal.fxml"),
    EDITOR_TEXTO("/principal/editor-texto.fxml"),
    PESTAÑAS_EDITOR("/principal/pestañas-editor.fxml"),
    WEB_VIEW("/principal/visor-html.fxml"),
    REPORTE_AST("/principal/reporte-ast.fxml"),
    REPORTE_DOM("/principal/reporte-dom.fxml"),
    REPORTE_ERROR("/principal/reporte-error.fxml");

    private final String path;

    RutaVista(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
