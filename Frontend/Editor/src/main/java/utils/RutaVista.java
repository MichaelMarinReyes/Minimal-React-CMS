package utils;

public enum RutaVista {
    EDITOR_TEXTO("/principal/editor-texto.fxml"),
    PESTAÑAS_EDITOR("/principal/pestañas-editor.fxml");

    private final String path;

    RutaVista(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
