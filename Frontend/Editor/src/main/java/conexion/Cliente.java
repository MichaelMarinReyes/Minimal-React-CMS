package conexion;

import java.io.*;

public class Cliente extends Conexion {
    private static Cliente instancia;

    private Cliente() throws IOException {
        super("cliente");
        this.enviarAlServer = new DataOutputStream(socket.getOutputStream());
        this.mensajeDelServer = new DataInputStream(socket.getInputStream());
    }

    public static Cliente getInstancia() throws IOException {
        if (instancia == null) {
            instancia = new Cliente();
        }
        return instancia;
    }

    private String enviarSolicitud(String metodo, String mensaje) {
        try {
            System.out.println(metodo + " -> ");
            enviarAlServer.writeUTF(mensaje);
            return mensajeDelServer.readUTF(); // RESPUESTA DEL SERVIDOR
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
}
