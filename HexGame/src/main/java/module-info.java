module edu.erciyes.hexgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.erciyes.hexgame to javafx.fxml;
    exports edu.erciyes.hexgame;
}