module com.svgs {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires org.apache.commons.lang3;
    requires org.apache.commons.text;
    requires javafx.graphics;
    opens com.svgs to javafx.fxml;
    exports com.svgs;
}
