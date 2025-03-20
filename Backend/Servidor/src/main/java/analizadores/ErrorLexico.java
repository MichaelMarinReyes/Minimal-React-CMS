package analizadores;

public class ErrorLexico {
    private String lexema;
    private int linea;
    private int colunma;
    private String tipo;
    private String descripcion;

    public ErrorLexico(String lexema, int linea, int colunma, String tipo, String descripcion) {
        this.lexema = lexema;
        this.linea = linea;
        this.colunma = colunma;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColunma() {
        return colunma;
    }

    public void setColunma(int colunma) {
        this.colunma = colunma;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
