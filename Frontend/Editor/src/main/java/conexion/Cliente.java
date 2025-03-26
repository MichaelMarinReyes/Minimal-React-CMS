package conexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cliente extends Conexion {
    private static Cliente instancia;

    private Cliente() throws IOException {
        super("cliente");
        this.enviarAlServer = new DataOutputStream(socket.getOutputStream());
        this.mensajeDelServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static Cliente getInstancia() throws IOException {
        if (instancia == null) {
            instancia = new Cliente();
        }
        return instancia;
    }

    private String enviarSolicitud(String metodo, String mensaje) {
        try {
            enviarAlServer.writeUTF(metodo + " " + mensaje);
            System.out.println("Enviado: " + mensaje);
            return mensajeDelServer.readLine(); // RESPUESTA DEL SERVIDOR
        } catch (IOException e) {
            return "ERROR al enviar mensaje: " + e.getMessage();
        }
    }

    public String get(String mensaje) {
        return enviarSolicitud("GET", mensaje);
    }

    public String post(String mensaje) {
        return enviarSolicitud("POST", mensaje);
    }

    public String patch(String mensaje) {
        return enviarSolicitud("PATCH", mensaje);
    }

    public String delete(String mensaje) {
        return enviarSolicitud("DELETE", mensaje);
    }

    public void cerrarConexion() {
        try {
            if (enviarAlServer != null) {
                enviarAlServer.close();
            }
            if (mensajeDelServer != null) {
                mensajeDelServer.close();
            }
            if (socket != null) {
                socket.close();
            }
            System.out.println("Conexión cerrada");
        } catch (IOException e) {
            System.out.println("ERROR al cerrar conexión: " + e.getMessage());
        }
    }

    /*
    public Cliente() throws IOException {
        super("cliente");
    }

    public void iniciarCliente() {
        try {
            enviaElServer = new DataOutputStream(socket.getOutputStream());
            mensajeDelServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Conectado");
            enviaElServer = new DataOutputStream(socket.getOutputStream());
            mensajeDelServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Conectado");
            for (int i = 0; i < 2; i++) {
                enviaElServer.writeUTF("Este esl el mensaje número " + (i+1) + "\n");
                System.out.println(mensajeDelServer.readLine());
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void enviarTextoAlServidor(String texto) {
        try {
            // Establece los flujos de entrada y salida
            enviaElServer = new DataOutputStream(socket.getOutputStream());
            mensajeDelServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Envía el texto al servidor
            enviaElServer.writeUTF(texto);
            System.out.println("Texto enviado: " + texto);
            System.out.println("Mensaje del servidor: " + mensajeDelServer.readLine());
        } catch (IOException e) {
            System.out.println("Error al enviar el texto: " + e.getMessage());
        }
    }*/
}
