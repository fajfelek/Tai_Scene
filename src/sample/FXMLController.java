package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

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

    HandlerChooser choser;
    static Map<String, String> wojew = new HashMap<>();
    static Map<String, String> miasta = new HashMap<>();
    private String streetName;
    private String selectedWojBox;
    private String wojNum;
    @FXML
    private void searchButtonClick(ActionEvent event) {

        tableView.getItems().clear();
//        ObservableList<Description> data = FXCollections.observableArrayList();
//
//            tableView.getItems().add(description1);

       streetName = nameText.getText();
       selectedWojBox = wojBox.getSelectionModel().getSelectedItem();
       wojNum = wojew.get(selectedWojBox.toUpperCase());

       System.out.println(streetName + " + " + selectedWojBox + " --> " + wojNum);

       Vector<Description> streetDescription = choser.chooser(null, wojNum, "01", streetName);

       System.out.println(streetDescription.size());

        for(int i = 0; i < streetDescription.size(); i++) {
            tableView.getItems().add(streetDescription.get(i));
            System.out.println(streetDescription.get(i));
        }
//                String key = entry.getKey();
//                String value = entry.getValue();
//                System.out.println(key);// + " + " + value);
//            }

//
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

        choser = new HandlerChooser();

        nameColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("NAME"));
        cechColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("CECH"));
        wojColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("WOJ"));
        powColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("POW"));
        gmiColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("GMI"));
        rodzColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("RODZ"));
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("CITY_NAME"));
        id_cityColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("ID_CITY"));
        id_nameColumn.setCellValueFactory(new PropertyValueFactory<Description,String>("ID_NAME"));

        wojBoxData.addAll("Wszystkie", "Podkarpackie", "Świętokrzyskie", "Wielkopolskie", "Opolskie",
                "Kujawsko-Pomorskie", "Małopolskie", "Warmińsko-Mazurskie", "Lubuskie", "Lubelskie",
                "Zachodniopomorskie", "Śląskie", "Łódzkie", "Mazowieckie", "Podlaskie", "Pomorskie", "Dolnośląskie");

        wojBox.setItems(wojBoxData);
        wojBox.getSelectionModel().select(0);

        wojew = choser.getWojew();
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
//            if (selectedPerson.equals("Wszystkie")){
//                powBox.setDisable(true);
//            } else {
//                powBox.setDisable(false);
//            }
//
//            if (wojew.containsKey(selectedPerson.toUpperCase())) {
//                System.out.println(wojew.get(selectedPerson.toUpperCase()));
//               System.out.println();
//            } else {
//                System.err.println("Nie dziala");
//            }

            System.out.println("ComboBox Action (selected: " + selectedPerson + ")");
        });
//        Vector<Description> streetDescription = choser.chooser(null, "02", "01", "Brzozowa");
//        System.out.println(streetDescription.size());

    }
}
