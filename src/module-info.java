/**
 * 
 */
/**
 * 
 */
module OOP {
    requires javafx.controls;
    requires javafx.fxml;
 
    opens AtDe.UserInterface to javafx.fxml;
    exports AtDe.UserInterface;
}