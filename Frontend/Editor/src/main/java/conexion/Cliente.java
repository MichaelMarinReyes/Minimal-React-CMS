package conexion;

import java.io.DataOutputStream;
import java.io.IOException;

public class Cliente extends Conexion {

    public Cliente() throws IOException {
        super("cliente");
    }

    public void iniciarCliente() {
        try {
            salidaServidor = new DataOutputStream(socket.getOutputStream());

            for (int i = 0; i < 2; i++) {
                salidaServidor.writeUTF("Este esl el mensaje número " + (i+1) + "\n");
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
