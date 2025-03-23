package conexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cliente extends Conexion {

    public Cliente() throws IOException {
        super("cliente");
    }

    public void iniciarCliente() {
        try {
            enviaElServer = new DataOutputStream(socket.getOutputStream());
            mensajeDelServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Conectado");

            Thread hiloCliente = new Thread(() -> {
                try {
                    // Mantener la conexión activa
                    while (true) {
                        // Puedes realizar alguna acción periódica, como recibir mensajes del servidor
                        String mensajeDelServidor = mensajeDelServer.readLine();
                        if (mensajeDelServidor != null) {
                            System.out.println("Mensaje del servidor: " + mensajeDelServidor);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error en la comunicación: " + e.getMessage());
                }
            });

            hiloCliente.setDaemon(true);  // Este hilo se cerrará automáticamente cuando la aplicación termine
            hiloCliente.start();  // Iniciar el hilo en segundo plano

//            enviaElServer = new DataOutputStream(socket.getOutputStream());
//            mensajeDelServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            System.out.println("Conectado");
//            for (int i = 0; i < 2; i++) {
//                enviaElServer.writeUTF("Este esl el mensaje número " + (i+1) + "\n");
//                System.out.println(mensajeDelServer.readLine());
//            }
//            socket.close();
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
    }
}
