package principal;

import conexion.ConexionWebSocket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //CONEXIÓN WEBSOCKET
        ConexionWebSocket.conectarWebSocket();
        //GUI
        Rectangle2D tamañoPantalla = Screen.getPrimary().getVisualBounds();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ventana-principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), tamañoPantalla.getWidth(), tamañoPantalla.getHeight());
        stage.setTitle("MINIMAL REACT CMS");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}