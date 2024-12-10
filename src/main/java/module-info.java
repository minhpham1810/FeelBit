module csci205_final_project {
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
//    requires org.postgresql.jdbc;
    requires com.google.gson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires jdk.jfr;
//    requires org.junit.jupiter;

    opens org.mahj.feelBitLogin to javafx.graphics;
    exports org.mahj;
    opens org.mahj to javafx.fxml;
    exports org.mahj.feelBitMoodTracking to javafx.fxml;
}

