module com.week2.synchronization {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.week2.synchronization to javafx.fxml;
    exports com.week2.synchronization;
}