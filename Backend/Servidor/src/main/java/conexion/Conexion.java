package conexion;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion {
    private final int PUERTO = 8080;
    private final String HOST = "localhost";
    protected String mensajeDelCliente;
    protected ServerSocket serverSocket;
    protected Socket socket;
    protected DataOutputStream enviarAlCliente;

    public Conexion(String tipo) throws IOException {
        if (tipo.equalsIgnoreCase("SERVIDOR")) {
            serverSocket = new ServerSocket(PUERTO);
            socket = new Socket();
        } else {
            socket = new Socket(HOST, PUERTO);
        }
    }
}
