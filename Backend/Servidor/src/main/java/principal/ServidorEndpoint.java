package principal;


import jakarta.websocket.server.ServerEndpoint;
import javax.websocket.*;


@ServerEndpoint(value = "/principal")
public class ServidorEndpoint {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Servidor: onOpen");
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println("Mensaje: " + message);
        return message;
    }

    @OnClose
    public void onClose(CloseReason closeReason) {
        System.out.println("Cerrando el servidor: " + closeReason);
    }
}
