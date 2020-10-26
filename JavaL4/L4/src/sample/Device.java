package sample;

import com.vlad.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class Device {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<devices> comboBoxDevice;

    @FXML
    private Button addDivice;

    @FXML
    private TextField NameDev;



    @FXML
    void initialize() {
        ObservableList<devices> enumValues = FXCollections.observableArrayList(devices.values());
        comboBoxDevice.getItems().addAll(enumValues);
        comboBoxDevice.setValue(devices.noType);
        comboBoxDevice.setEditable(false);

        addDivice.setOnAction(actionEvent -> {
            smart_devices dev=null;
            String NameD= NameDev.getText();
            if(NameD.equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка создания...");
                alert.setHeaderText("Имя пустое");
                alert.showAndWait();
                return;
            }
            switch (comboBoxDevice.getValue()){
                case air_conditioning -> {
                    dev=new air_conditioning(NameD);
                }
                case robot_vacuum_cleaner -> {
                    dev = new robot_vacuum_cleaner(NameD);
                }
                case dishwasher -> {
                    dev = new dishwasher(NameD);
                }
                case heater -> {
                    dev = new heater(NameD);
                }
                case TV -> {
                    dev = new tv(NameD);
                }
                case noType -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка создания...");
                    alert.setHeaderText("Неудалось создать утройство, так как небыл выбран тип");
                    alert.showAndWait().ifPresent(rs -> {
                    });
                }
            }
            if(dev!=null) Controller.smart_devices.add(dev);
            Stage stage = (Stage) addDivice.getScene().getWindow();
            stage.close();
        });
    }
}