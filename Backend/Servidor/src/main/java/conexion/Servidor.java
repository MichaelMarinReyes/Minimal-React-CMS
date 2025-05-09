package conexion;

import analizadores.Lexer;
import analizadores.Parser;
import principal.Analizar;

import java.io.*;

public class Servidor extends Conexion {
    private Analizar analizar;
    public Servidor() throws IOException {
        super("servidor");
    }

    public void iniciarServer() {
        try {
            System.out.println("Servidor Funcionando");
            socket = serverSocket.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            enviarAlCliente = new DataOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            while (true) {
                mensajeDelCliente = entrada.readUTF();
                analizar = new Analizar(mensajeDelCliente);
                //System.out.println("Cadena que se va a analizar: " + mensajeDelCliente);
                enviarAlCliente.writeUTF(analizar.analizarAntlr4());
                //enviarAlCliente.writeUTF(analizar.analizar()); no funciona con cup y flex
                //enviarAlCliente.writeUTF("mensaje recibido");
            }
            /*System.out.println("Fin servidor");
            serverSocket.close();*/
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
