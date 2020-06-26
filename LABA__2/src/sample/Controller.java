package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private Tab viewTab;

    @FXML
    private AnchorPane viewPage;

    @FXML
    private Tab addTab;

    @FXML
    private AnchorPane addPage;

    @FXML
    private Button addBtn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField numField;

    @FXML
    private Tab updateTab;

    @FXML
    private AnchorPane updatePage;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField1;

    @FXML
    private TextField surnameField1;

    @FXML
    private TextField streetField1;

    @FXML
    private TextField numField1;

    @FXML
    private Button updateBtn;

    @FXML
    private Tab deleteTab;

    @FXML
    private AnchorPane deletePage;

    @FXML
    private TextField deleteField;

    @FXML
    private Pane pane;

    @FXML
    private Button reBtn;

    @FXML
    private Button deleteBtn;
    @FXML
    public void initialize(){
    show();

    addBtn.setOnAction(event -> {
    DatabaseHandler db = new DatabaseHandler();
    db.add(nameField.getText(), surnameField.getText(), streetField.getText(), numField.getText());
    });
    deleteBtn.setOnAction(event -> {
        DatabaseHandler db = new DatabaseHandler();
        try {
            db.delete(Integer.parseInt(deleteField.getText().trim()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    });
    updateBtn.setOnAction(event -> {
    DatabaseHandler db = new DatabaseHandler();
    db.update(nameField1.getText(), surnameField1.getText(), streetField1.getText(), numField1.getText(), Integer.parseInt(idField.getText()));
    });
    reBtn.setOnAction(event -> {
        show();
    });
    }

    public void show(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getAdres();
        ObservableList<Adres> adr = FXCollections.observableArrayList();

        try {
            while (result.next()) {
                Adres adres = new Adres();
                adres.setId(result.getInt(1));
                adres.setName(result.getString(2));
                adres.setSurname(result.getString(3));
                adres.setStreet(result.getString(4));
                adres.setNumber(result.getString(5));
                adr.add(adres);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TableView<Adres> tableView = new TableView<Adres>(adr);
        tableView.setPrefWidth(550);
        tableView.setPrefHeight(450);
        tableView.setLayoutX(10);
        tableView.setLayoutY(10);

        TableColumn<Adres, Integer> idCol = new TableColumn<Adres, Integer>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Adres, Integer>("id"));
        tableView.getColumns().add(idCol);

        TableColumn<Adres, String> nameCol = new TableColumn<Adres, String>("Имя");
        nameCol.setCellValueFactory(new PropertyValueFactory<Adres, String>("name"));
        tableView.getColumns().add(nameCol);

        TableColumn<Adres, String> surnameCol = new TableColumn<Adres, String>("Фамилия");
        surnameCol.setCellValueFactory(new PropertyValueFactory<Adres, String>("surname"));
        tableView.getColumns().add(surnameCol);

        TableColumn<Adres, String> streetCol = new TableColumn<Adres, String>("Улица");
        streetCol.setCellValueFactory(new PropertyValueFactory<Adres, String>("street"));
        tableView.getColumns().add(streetCol);

        TableColumn<Adres, String> numCol = new TableColumn<Adres, String>("Номер дома");
        numCol.setCellValueFactory(new PropertyValueFactory<Adres, String>("number"));
        tableView.getColumns().add(numCol);
        pane.getChildren().add(tableView);
    }
}
