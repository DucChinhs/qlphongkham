package control;

import general.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MaintabControl {

    private ArrayList<user> userArrayList = new ArrayList<>();

    @FXML
    private Text notice;

    @FXML
    private Button login;

    @FXML
    private TextField pass;

    @FXML
    private TextField username;

    @FXML
    void loginPress(ActionEvent event) throws IOException {
        readFile();
        if (check()) {
            Parent root = null;
            Stage stage = null;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("FxmlFile\\mainMenu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            notice.setText("sai tài khoản");
        }
    }

    private void readFile() throws FileNotFoundException {
        FileReader fr = new FileReader(finalClass.headerFile + "account.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while (true) {
            try {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String Txt[] = line.split("-");
                user userInfo = new user(Txt[0],Txt[1]);
                userArrayList.add(userInfo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Boolean check() {
        for (user userInfo : userArrayList) {
            if (userInfo.getUser().equals(username.getText()) && userInfo.getPass().equals(pass.getText())) {
                finalClass.currentUser = userInfo;
                return true;
            }
        }
        return false;
    }

}
