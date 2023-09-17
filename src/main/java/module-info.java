module co.edu.uniquindio.banco_tcp {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.banco_tcp to javafx.fxml;
    exports co.edu.uniquindio.banco_tcp;
    exports co.edu.uniquindio.banco_tcp.client.controller;
    opens co.edu.uniquindio.banco_tcp.client.controller to javafx.fxml;
    opens co.edu.uniquindio.banco_tcp.client.model to javafx.base;
}