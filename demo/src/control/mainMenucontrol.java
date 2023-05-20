package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class mainMenucontrol {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    void btn1press(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FxmlFile\\qlbenhnhan.fxml"));
        anchorpane.getChildren().setAll(root);
    }

    @FXML
    void btn2press(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FxmlFile\\qlbenhan.fxml"));
        anchorpane.getChildren().setAll(root);
    }

    @FXML
    void btn3press(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FxmlFile\\qlphongbenh.fxml"));
        anchorpane.getChildren().setAll(root);
    }

}