package control;

import general.benhan;
import general.phongdieutri;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class qlphongbenh implements Initializable {

    private ArrayList<phongdieutri> benhnhanArrayList = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ObservableList<phongdieutri> list = FXCollections.observableArrayList();

    @FXML
    private TextField IDtextBox;

    @FXML
    private TextField Namebox;

    @FXML
    private TableColumn<phongdieutri, String> cl1;

    @FXML
    private TableColumn<phongdieutri, String> cl2;

    @FXML
    private TableColumn<phongdieutri, String> cl3;

    @FXML
    private TableColumn<phongdieutri, String> cl4;

    @FXML
    private TableColumn<phongdieutri, String> cl5;

    @FXML
    private TextField passbox;

    @FXML
    private TextField phonebox;

    @FXML
    private TextField searchText;

    @FXML
    private TableView<phongdieutri> table;

    @FXML
    private TextField userbox;

    @FXML
    void addpress(ActionEvent event) throws IOException {
        String newsv =  IDtextBox.getText() +  "-" + Namebox.getText() + "-" + userbox.getText() + "-" + passbox.getText() + "-" + phonebox.getText();
        if (check()) {
            WritetoUserList(newsv);
        }
    }

    @FXML
    void deletepress(ActionEvent event) throws IOException {
        phongdieutri userInfo = table.getSelectionModel().getSelectedItem();
        int number = -1;
        for (int i  = 0 ; i < list.size() ; i++) {
            if (list.get(i) == userInfo) {
                number = i;
                break;
            }
        }
        arrayList.remove(number);
        WriteArrayList();
    }

    public Boolean check() {
        for (phongdieutri userInfo : list) {
            if (userInfo.getID().equals(IDtextBox.getText())) {
                return false;
            }
        }
        return true;
    }

    @FXML
    void editpress(ActionEvent event) throws IOException {
        phongdieutri userInfo = table.getSelectionModel().getSelectedItem();
        String newsv = IDtextBox.getText() +  "-" + Namebox.getText() + "-" + userbox.getText() + "-" + passbox.getText() + "-" + phonebox.getText();
        int number = -1;
        for (int i  = 0 ; i < list.size() ; i++) {
            if (list.get(i) == userInfo) {
                number = i;
                break;
            }
        }
        arrayList.set(number, newsv);
        WriteArrayList();
    }

    public void readData() {
        try {
            FileReader fr = new FileReader(finalClass.headerFile + "phongbenh.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                arrayList.add(line);
                String[] Txt = line.split("-");
                phongdieutri userInfo = new phongdieutri(Txt[0],Txt[1],Txt[2],Txt[3],Txt[4]);
                benhnhanArrayList.add(userInfo);
                list.add(userInfo);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchMethod() {
        FilteredList<phongdieutri> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(phongdieutri -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (phongdieutri.getID().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (phongdieutri.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (phongdieutri.getBacsidieutri().indexOf(lowerCaseFilter)!=-1) {
                    return true;
                } else if (phongdieutri.getTenbenhnhan().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (phongdieutri.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<phongdieutri> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

    public void WritetoUserList(String st) throws IOException {
        FileWriter fw = new FileWriter(finalClass.headerFile + "phongbenh.txt",true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(st);
        bw.close();
        fw.close();
    }

    public void WriteArrayList() throws IOException {
        FileWriter fileWriter = new FileWriter(finalClass.headerFile + "phongbenh.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(arrayList.get(0));
        bufferedWriter.close();
        fileWriter.close();
        for (int i = 1 ; i < arrayList.size() ; i++) {
            if (arrayList.get(i) != null) {
                fileWriter = new FileWriter(finalClass.headerFile + "phongbenh.txt",true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.newLine();
                bufferedWriter.write(arrayList.get(i));
                bufferedWriter.close();
                fileWriter.close();
            } else {
                break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readData();
        cl1.setCellValueFactory(new PropertyValueFactory<phongdieutri, String>("ID"));
        cl2.setCellValueFactory(new PropertyValueFactory<phongdieutri, String>("bacsidieutri"));
        cl3.setCellValueFactory(new PropertyValueFactory<phongdieutri, String>("ytaphutrach"));
        cl4.setCellValueFactory(new PropertyValueFactory<phongdieutri, String>("tenbenhnhan"));
        cl5.setCellValueFactory(new PropertyValueFactory<phongdieutri, String>("date"));
        searchMethod();
    }
}
