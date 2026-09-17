module com.zmiko.onara {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.zmiko.onara to javafx.fxml;
    exports com.zmiko.onara;
}