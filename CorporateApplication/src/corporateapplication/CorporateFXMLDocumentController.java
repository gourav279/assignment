/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corporateapplication;

import corporateapplication.corporator.Corporator;
import corporateapplication.corporator.CorporatorImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.util.Callback;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 *
 * @author unique
 */
public class CorporateFXMLDocumentController implements Initializable {

    private static String value;

    @FXML
    private AnchorPane actionPane, editPane, addPane;

    @FXML
    private TextField search,editName, editId, editAccNum, addName, addId, addAccNum;

    @FXML
    private TableView<Corporator> corporateTable;

    @FXML
    private TableColumn<Corporator, String> corporateName, corporateId, accountantNumber;

    @FXML
    private TableColumn action;

    @FXML
    private Button addpanebtn, editbtn, addbtn, backbtn;

    public void corporateList() {
        ObservableList<Corporator> listM;
        corporateName.setCellValueFactory(new PropertyValueFactory<Corporator, String>("corporateName"));
        corporateId.setCellValueFactory(new PropertyValueFactory<Corporator, String>("corporateId"));
        accountantNumber.setCellValueFactory(new PropertyValueFactory<Corporator, String>("accountantNumbers"));
        CorporatorImpl c = new CorporatorImpl();
        listM = c.getAllCorporator();
        corporateTable.setItems(listM);

    }

    void search_corporate() {
        ObservableList<Corporator> listM;
        corporateName.setCellValueFactory(new PropertyValueFactory<Corporator, String>("corporateName"));
        corporateId.setCellValueFactory(new PropertyValueFactory<Corporator, String>("corporateId"));
        accountantNumber.setCellValueFactory(new PropertyValueFactory<Corporator, String>("accountantNumbers"));
        CorporatorImpl c = new CorporatorImpl();
        listM = c.getAllCorporator();
        corporateTable.setItems(listM);
        FilteredList<Corporator> filteredData = new FilteredList<>(listM, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getCorporateName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (person.getCorporateId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (person.getAccountantNumbers().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Corporator> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(corporateTable.comparatorProperty());
        corporateTable.setItems(sortedData);
    }

    void clearText() {
        addName.clear();
        addId.clear();
        addAccNum.clear();
        editName.clear();
        editId.clear();
        editAccNum.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search_corporate();
        Callback<TableColumn<Corporator, String>, TableCell<Corporator, String>> cellco = (params) -> {
            final TableCell<Corporator, String> cell = new TableCell<Corporator, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //ensure that cell is creates only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        ComboBox com = new ComboBox();
                        com.setPromptText("Action Act");
                        com.getItems().addAll("Delete", "Edit");
                        com.setOnAction(event -> {
                            value = (String) com.getValue();
                            if (value.equals("Edit")) {
                                actionPane.setVisible(true);
                                addPane.setVisible(false);
                                editPane.setVisible(true);
                                Corporator c = getTableView().getItems().get(getIndex());
                                CorporatorImpl cimpl = new CorporatorImpl();
                                editName.setText(c.getCorporateName());
                                editId.setText(c.getCorporateId());
                                editAccNum.setText(c.getAccountantNumbers());
                                editbtn.setOnAction(e -> {
                                    if (editId.getText().length() != 7 && editAccNum.getText().length() != 10) {
                                        Window owner = editbtn.getScene().getWindow();
                                        showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                                                "Please Enter Vaild Id and Account Number");
                                        actionPane.setVisible(true);
                                    }
                                    if (editId.getText().length() == 7 && editAccNum.getText().length() == 10) {
                                        Corporator c1 = new Corporator(c.getId(), editName.getText(), editId.getText(), editAccNum.getText());
                                        cimpl.update(c1);
                                        actionPane.setVisible(false);
                                        Window owner = addbtn.getScene().getWindow();
                                        showAlert(Alert.AlertType.CONFIRMATION, owner, "Form Confirmation!",
                                                "Are you want to insert?");
                                        clearText();
                                        corporateList();
                                    }
                                });
                            } else if (value.equals("Delete")) {
                                Corporator c = getTableView().getItems().get(getIndex());
                                CorporatorImpl cimpl = new CorporatorImpl();
                                cimpl.delete(Integer.parseInt(c.getId()));
                                corporateList();
                            }
                        });
                        setGraphic(com);
                    }
                }
            };
            return cell;
        };
        action.setCellFactory(cellco);
        addpanebtn.setOnAction(e -> {
            actionPane.setVisible(true);
            addPane.setVisible(true);
            editPane.setVisible(false);
        });
        backbtn.setOnAction(e -> {
            actionPane.setVisible(false);
        });
        addbtn.setOnAction(e -> {
            actionPane.setVisible(false);
            CorporatorImpl cimpl = new CorporatorImpl();
            if (addId.getText().length() != 7 && addAccNum.getText().length() != 10) {
                Window owner = addbtn.getScene().getWindow();
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please Enter Vaild Id and Account Number");
                actionPane.setVisible(true);
            }
            if (addId.getText().length() == 7 && addAccNum.getText().length() == 10) {
                Corporator corporator = new Corporator(addName.getText(), addId.getText(), addAccNum.getText());
                cimpl.insert(corporator);
                Window owner = addbtn.getScene().getWindow();
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Form Confirmation!",
                        "Are you want to insert?");
                clearText();
                corporateList();
            }
        });
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();//To change body of generated methods, choose Tools | Templates.
    }

}
