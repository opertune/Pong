module com.example.pong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pong to javafx.fxml;
    exports com.example.pong;
}