module com.svgs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens com.svgs to javafx.fxml;
    exports com.svgs;
}
