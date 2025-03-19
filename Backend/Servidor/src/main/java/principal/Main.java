package principal;

import org.glassfish.tyrus.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server("localhost", 8080, "/server", ServidorEndpoint.class);
        try {
            server.start();
            System.out.println("Server corriendo");
            while (true) {}
        } catch (Exception e) {
            //System.out.println("Error al ejecutar el servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}