package principal;

import conexion.Cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.RutaVista;


import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //CONEXIÓN WEBSOCKET
        /*try {
            Cliente cliente = Cliente.getInstancia();
            System.out.println("Cliente conectado: ");
        } catch (IOException e) {
            System.err.println("Error al conectar el servidor " + e.getMessage());
        }*/
        /*Cliente cliente = new Cliente();
        System.out.println("Iniciando cliente");
        cliente.iniciarCliente();*/
        //GUI
        Rectangle2D tamañoPantalla = Screen.getPrimary().getVisualBounds();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(RutaVista.VENTANA.getPath()));
        Scene scene = new Scene(fxmlLoader.load(), tamañoPantalla.getWidth(), tamañoPantalla.getHeight());
        stage.setTitle("MINIMAL REACT CMS");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        try {
            Cliente.getInstancia().cerrarConexion();
            System.out.println("Cliente desconectado");
        } catch (IOException e) {
            System.err.println("Error al desconectar el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}