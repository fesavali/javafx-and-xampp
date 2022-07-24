module com.example.hw3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hw3 to javafx.fxml;
    exports com.example.hw3;
}