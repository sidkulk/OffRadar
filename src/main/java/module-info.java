module io.sidkulk.offradar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.codec;
    requires java.sql;

    opens io.sidkulk.offradar to javafx.fxml;
    exports io.sidkulk.offradar;
}