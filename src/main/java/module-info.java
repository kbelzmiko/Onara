module com.zmiko.onara {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.zmiko.onara to javafx.fxml;
    exports com.zmiko.onara;
}