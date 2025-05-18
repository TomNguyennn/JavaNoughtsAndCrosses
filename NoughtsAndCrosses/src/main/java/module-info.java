module org.example.lab8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens NoughtsAndCrosses to javafx.fxml;
    exports NoughtsAndCrosses;
}