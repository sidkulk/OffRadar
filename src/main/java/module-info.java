module io.sidkulk.offradar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.apache.commons.codec;
    requires java.sql;
    requires sqlite.jdbc;

    opens io.sidkulk.offradar to javafx.fxml;
    exports io.sidkulk.offradar;
}