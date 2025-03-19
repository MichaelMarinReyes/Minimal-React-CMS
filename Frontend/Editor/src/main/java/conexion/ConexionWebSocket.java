package conexion;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

@ClientEndpoint
public class ConexionWebSocket {
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Conexion establecida con exito");
        try {
            session.getBasicRemote().sendText("start");
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor " + e.getMessage());
        }
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Recibiendo mensaje " + message);
            String entradaTexto = bufferedReader.readLine();
            return entradaTexto;
        } catch (IOException e) {
            System.out.println("Error al recibir mensaje " + e.getMessage());
        }
        return "";
    }

    @OnClose
    public void onClose(CloseReason closeReason) {
        System.out.println("Cerrando con exito");
        System.out.println("Cerrando por: " + closeReason);
    }

    public static void conectarWebSocket() {
        ClientManager client = ClientManager.createClient();
        try {
            URI uri = new URI("ws://localhost:8080/server/principal");
            client.connectToServer(new ConexionWebSocket(), uri);
            System.out.println("Presiona Enter para cerrar la conexión.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();
        } catch (URISyntaxException | InternalError e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        }
    }
}
