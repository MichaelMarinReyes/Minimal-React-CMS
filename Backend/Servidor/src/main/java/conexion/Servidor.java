package conexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Servidor extends Conexion {
    public Servidor() throws IOException {
        super("servidor");
    }

    public void iniciarServer() {
        try {
            System.out.println("Iniciado");
            socket = serverSocket.accept();
            System.out.println("Cliente conectado");
            salidaCliente = new DataOutputStream(socket.getOutputStream());
            salidaCliente.writeUTF("Petición recibida");
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while ((mensajeEntrante = entrada.readLine()) != null) {
                System.out.println(mensajeEntrante);
            }
            System.out.println("Fin servidor");
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

}
