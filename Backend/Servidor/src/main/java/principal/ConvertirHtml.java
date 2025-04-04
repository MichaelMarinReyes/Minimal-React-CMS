package principal;

public class ConvertirHtml {
    private String textoParser;
    private String textoEscape;
    private String pathHtml = "";

    public String getTextoEscape() {
        return textoEscape;
    }

    public void convertirHtml(String textoParser) {
        this.textoParser = textoParser;
        this.textoEscape = textoParser;
    }

    public String obtenerHtml() {
        //abrir path de pathHtml
        return textoEscape;
    }
}
