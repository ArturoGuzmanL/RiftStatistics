module main.riftstatistics.rift {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens main.riftstatistics.rift to javafx.fxml;
    exports main.riftstatistics.rift;
    exports main.riftstatistics.rift.Controllers;
    opens main.riftstatistics.rift.Controllers to javafx.fxml;
}