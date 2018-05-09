package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    @FXML
    private TableView<Description> tableView;
    @FXML
    private TableColumn<Description, String> nameColumn;
    @FXML
    private TableColumn<Description, String> cechColumn;
    @FXML
    private TableColumn<Description, String> wojColumn;
    @FXML
    private TableColumn<Description, String> powColumn;
    @FXML
    private TableColumn<Description, String> gmiColumn;
    @FXML
    private TableColumn<Description, String> rodzColumn;
    @FXML
    private TableColumn<Description, String> cityNameColumn;
    @FXML
    private TableColumn<Description, String> id_cityColumn;
    @FXML
    private TableColumn<Description, String> id_nameColumn;

    @FXML
    private TextField nameText;

    @FXML
    private ComboBox<String> wojBox;
    private ObservableList<String> wojBoxData = FXCollections.observableArrayList();

    @FXML
    private ComboBox powBox;

    @FXML
    private Button searchButton;

    Description description1 = new Description("01",
            "02", "03", "04", "05",
            "06", "07", "08");

    @FXML
    private void searchButtonClick(ActionEvent event) {

        tableView.getItems().clear();
        ObservableList<Description> data = FXCollections.observableArrayList();

            tableView.getItems().add(description1);

        String value = (String) wojBox.getValue();

        System.out.println(value);

        tableView.refresh();
    }

//
//    final List options = wojBox.getItems();
//    wojBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
//        @Override public void changed(ObservableValue ov, Number oldSelected, Number newSelected) {
//            System.out.println("Old Selected Option: " + options.get(oldSelected.intValue()));
//            System.out.println("New Selected Option: " +options.get(newSelected.intValue()));
//        }
//    })



    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //tableView.setEditable(true);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("NAME"));
        cechColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("CECH"));
        wojColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("WOJ"));
        powColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("POW"));
        gmiColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("GMI"));
        rodzColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("RODZ"));
        //cityNameColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("NAME"));
        id_cityColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("ID_CITY"));
        id_nameColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("ID_NAME"));

        wojBoxData.addAll("Podkarpackie", "Małopolskie", "Lubuskie", "Mazowieckie");
        wojBox.setItems(wojBoxData);


//        wojBox.setCellFactory((comboBox) -> {
//            return new ListCell<String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (item == null || empty) {
//                        setText(null);
//                    } else {
//                        setText(item);
//                    }
//                }
//            };
//        });

        wojBox.setOnAction((event) -> {
            String selectedPerson = wojBox.getSelectionModel().getSelectedItem();
            if (selectedPerson.equals("Lubuskie")){
                powBox.setDisable(true);
            }
            System.out.println("ComboBox Action (selected: " + selectedPerson.toString() + ")");
        });


//        wojBox.getItems().addAll("Podkarpackie", "Małopolskie", "Lubuskie", "Mazowieckie");
//        if (wojBox.getValue() != null)
//            powBox.setDisable(false);
//        powBox.getItems().addAll("Podkarpackie", "Małopolskie", "Lubuskie", "Mazowieckie");
    }
}
