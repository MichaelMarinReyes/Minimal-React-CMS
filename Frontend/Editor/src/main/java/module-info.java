module proyecto1.editor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javax.websocket.api;
    requires tyrus.client;
    requires java.desktop;

    opens principal to javafx.fxml;
    exports principal;
    exports controllers;
    opens controllers to javafx.fxml;
}