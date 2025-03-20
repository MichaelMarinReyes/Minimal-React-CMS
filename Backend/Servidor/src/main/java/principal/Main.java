package principal;

import conexion.Servidor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        System.out.println("Iniciando servidor...");
        servidor.iniciarServer();
    }
}