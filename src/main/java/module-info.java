module main.riftstatistics.rift {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
    requires java.sql;
    requires org.apache.commons.codec;
    requires mysql.connector.java;
    requires commons.validator;

    opens main.riftstatistics.rift to javafx.fxml;
    exports main.riftstatistics.rift;
    exports main.riftstatistics.rift.Controllers;
    opens main.riftstatistics.rift.Controllers to javafx.fxml;
    exports main.riftstatistics.rift.BDDConnection;
    opens main.riftstatistics.rift.BDDConnection to javafx.fxml;
}