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
    private ObservableList<String> powBoxData = FXCollections.observableArrayList();

    @FXML
    private Button searchButton;

    HandlerChooser choser;
    static Map<String, String> wojew = new HashMap<>();
    static Map<String, String> miasta = new HashMap<>();
    static HashMap<String, HashMap<String, String>> outerPowiat;
    static HashMap<String, String> innerPowiat;

    private String streetName;
    private String selectedWojBox;
    private String wojNum;
    private String powNum;


    private String selectedPowBox;
    @FXML
    private void searchButtonClick(ActionEvent event) {
        tableView.getItems().clear();
        streetName = nameText.getText();
        selectedWojBox = wojBox.getSelectionModel().getSelectedItem();
        wojNum = wojew.get(selectedWojBox.toUpperCase());

        if (outerPowiat.containsKey(wojNum))
        {
            innerPowiat = getInnerPowiat(wojNum);
            for(Map.Entry<String, String> entry : innerPowiat.entrySet()) {
                powBoxData.addAll(entry.getValue());
            }
        }

        System.out.println(streetName + " + " + selectedWojBox + " --> " + wojNum);

        if(!powBox.isDisable()) {
            selectedPowBox = (String) powBox.getSelectionModel().getSelectedItem();
            if(selectedPowBox.equals("Wszystkie")){
                powNum = null;
            } else {
                selectedWojBox = wojBox.getSelectionModel().getSelectedItem();
                String wojNum = wojew.get(selectedWojBox.toUpperCase());
                innerPowiat = getInnerPowiat(wojNum);

                System.out.println(selectedPowBox);
                for (HashMap.Entry<String, String> entry : innerPowiat.entrySet()) {

                    if (entry.getValue().equals(selectedPowBox)) {
                        powNum = entry.getKey();
                        break;
                    }
                }
            }
        } else {
            powNum = null;
        }

System.out.println(wojNum + " + " + powNum + " + " + streetName);
       Vector<Description> streetDescription = choser.chooser(wojNum, powNum, streetName);

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


    public HashMap<String,String> getInnerPowiat(String key1) {
        HashMap innerMap = outerPowiat.get(key1);
        if (innerMap==null) {
            return null;
        }
        return innerMap;
    }

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
        powBox.setDisable(true);
        powBox.getSelectionModel().select( "Wszystkie");

        wojew = choser.getWojew();
        outerPowiat = choser.getOuterPowiat();

        wojBox.setOnAction((event) -> {
            selectedWojBox = wojBox.getSelectionModel().getSelectedItem();
            String wojNum = wojew.get(selectedWojBox.toUpperCase());
            powBox.getSelectionModel().select("Wszystkie");

            if (selectedWojBox.equals("Wszystkie")){
                powBox.setDisable(true);
                powBox.getSelectionModel().select( "Wszystkie");
            } else {
                powBox.setDisable(false);
            }

            if (outerPowiat.containsKey(wojNum))
            {
                powBoxData.clear();
                powBoxData.add("Wszystkie");
                System.err.println("JEST " + wojNum);
                innerPowiat = getInnerPowiat(wojNum);
                for(Map.Entry<String, String> entry : innerPowiat.entrySet()) {
                    powBoxData.addAll(entry.getValue());
                }
                powBox.setItems(powBoxData);
                powBox.getSelectionModel().select(0);
            } else {
                System.err.println("Nie ma");
            }

            System.out.println("ComboBox Action (selected: " + selectedWojBox + ")");
        });

    }

}
