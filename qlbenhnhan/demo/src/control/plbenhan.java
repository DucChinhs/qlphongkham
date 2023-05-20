package control;

import general.benhan;
import general.benhnhan;
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

public class plbenhan implements Initializable {

    private ArrayList<benhan> benhnhanArrayList = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ObservableList<benhan> list = FXCollections.observableArrayList();

    @FXML
    private TextField IDtextBox;

    @FXML
    private TextField Namebox;

    @FXML
    private TableColumn<benhan, String> cl1;

    @FXML
    private TableColumn<benhan, String> cl2;

    @FXML
    private TableColumn<benhan, String> cl3;

    @FXML
    private TableColumn<benhan, String> cl4;

    @FXML
    private TableColumn<benhan, String> cl5;

    @FXML
    private TextField passbox;

    @FXML
    private TextField phonebox;

    @FXML
    private TextField searchText;

    @FXML
    private TableView<benhan> table;

    @FXML
    private TextField userbox;

    @FXML
    void addpress(ActionEvent event) throws IOException {
        benhan userInfo = table.getSelectionModel().getSelectedItem();
        String newsv =  IDtextBox.getText() +  "-" + Namebox.getText() + "-" + userbox.getText() + "-" + passbox.getText() + "-" + phonebox.getText();
        if (check()) {
            WritetoUserList(newsv);
        }
    }

    public Boolean check() {
        for (benhan userInfo : list) {
            if (userInfo.getId().equals(IDtextBox.getText())) {
                return false;
            }
        }
        return true;
    }

    @FXML
    void deletepress(ActionEvent event) throws IOException {
        benhan userInfo = table.getSelectionModel().getSelectedItem();
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

    @FXML
    void editpress(ActionEvent event) throws IOException {
        benhan userInfo = table.getSelectionModel().getSelectedItem();
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
            FileReader fr = new FileReader(finalClass.headerFile + "benhan.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                arrayList.add(line);
                String[] Txt = line.split("-");
                benhan userInfo = new benhan(Txt[0],Txt[1],Txt[2],Txt[3],Txt[4]);
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
        FilteredList<benhan> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(benhan -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (benhan.getId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (benhan.getMota().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (benhan.getIdbenhnhan().indexOf(lowerCaseFilter)!=-1) {
                    return true;
                } else if (benhan.getBsphutrach().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (benhan.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<benhan> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

    public void WritetoUserList(String st) throws IOException {
        FileWriter fw = new FileWriter(finalClass.headerFile + "benhan.txt",true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(st);
        bw.close();
        fw.close();
    }

    public void WriteArrayList() throws IOException {
        FileWriter fileWriter = new FileWriter(finalClass.headerFile + "benhan.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(arrayList.get(0));
        bufferedWriter.close();
        fileWriter.close();
        for (int i = 1 ; i < arrayList.size() ; i++) {
            if (arrayList.get(i) != null) {
                fileWriter = new FileWriter(finalClass.headerFile + "benhan.txt",true);
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
        cl1.setCellValueFactory(new PropertyValueFactory<benhan, String>("id"));
        cl2.setCellValueFactory(new PropertyValueFactory<benhan, String>("date"));
        cl3.setCellValueFactory(new PropertyValueFactory<benhan, String>("idbenhnhan"));
        cl4.setCellValueFactory(new PropertyValueFactory<benhan, String>("bsphutrach"));
        cl5.setCellValueFactory(new PropertyValueFactory<benhan, String>("mota"));
        searchMethod();
    }
}