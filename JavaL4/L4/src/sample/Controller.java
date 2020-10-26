package sample;

import com.vlad.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FlowPane FlowPaneDev;

    @FXML
    private Button buttonAdd;

    @FXML
    private Text label1;

    @FXML
    private Button buttonOperation;

    @FXML
    private Text label2;

    @FXML
    private Button buttonDel;

    @FXML
    private ListView<String> listBox;

    @FXML
    private ListView<String> operationList;

    @FXML
    private Text TextBox1;

    @FXML
    private TextArea TextState;

    static ArrayList<com.vlad.smart_devices> smart_devices = new ArrayList();


    @FXML
    void initialize() {

       buttonAdd.setOnAction(actionEvent -> {
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/sample/device.fxml"));
           try {
               loader.load();
           } catch (IOException e) {
               e.printStackTrace();
           }

           Parent root = loader.getRoot();
           Stage stage = new Stage();

           stage.setScene(new Scene(root));
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setTitle("Add device");
           stage.showAndWait();
           ShowDev();
       });

        buttonOperation.setOnAction(actionEvent -> {
            int choice=operationList.getSelectionModel().getSelectedIndex();
            choiceSelect(choice);
        });

        buttonDel.setOnAction(actionEvent -> {
            int choice=listBox.getSelectionModel().getSelectedIndex();
            smart_devices.remove(choice);
            ShowDev();
        });

        listBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {
                if(RefreshOper())
                    if(smart_devices.size()!=0){
                     label2.setText(smart_devices.get(listBox.getSelectionModel().getSelectedIndex()).getName());
                    }
                }
        });

        operationList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    int currentItemSelected = operationList.getSelectionModel().getSelectedIndex();
                    choiceSelect(currentItemSelected);
                }
            }
        });
    }

    void ShowDev(){
        TextState.clear();
        if(smart_devices.size()==0){
            TextBox1.setText("У вас нет устройст");
            showDisplayDev(false);
            listBox.setVisible(false);
        }
        else {
            TextBox1.setText("Ваши Устройста:");
            showDisplayDev(true);
            ArrayList<String> ss=new ArrayList<>();
            String prefix="";
            for(com.vlad.smart_devices s : smart_devices){
                switch (s.getTypeDevices()){
                    case robot_vacuum_cleaner -> prefix = "rvc";
                    case air_conditioning -> prefix="ac";
                    case TV -> prefix="TV";
                    case dishwasher -> prefix="d";
                    case heater -> prefix="h";
                }
                ss.add("["+prefix+"]"+s.getName());

            }
            ObservableList<String> d = FXCollections.observableArrayList(ss);
            listBox.setItems(d);
        }
    }

    boolean RefreshOper() {
            if(listBox.getSelectionModel().getSelectedIndex()==-1) return false;
            ArrayList<String> s1 =new ArrayList<>();

            s1.add("[1] Изменить имя");
            if(smart_devices.get(listBox.getSelectionModel().getSelectedIndex()).isState())
                s1.add("[2] Выключить");
            else
                s1.add("[2] Включить");

            switch (smart_devices.get(listBox.getSelectionModel().getSelectedIndex()).getTypeDevices()){
                case noType -> {
                    //return 120;
                }
                case air_conditioning, heater -> {
                    s1.add("[3] Текущая температура");
                    s1.add("[4] Повысить температуру (+1°)");
                    s1.add("[5] Понизить температуру (-1°)");
                    s1.add("[6] Задать температуру");
                }
                case robot_vacuum_cleaner -> {
                    s1.add("[3] Показать комнаты");
                    s1.add("[4] Добавить комнату");
                    s1.add("[5] Удалить комнату");
                    s1.add("[6] Отправить убирать");
                }
                case TV -> {
                    s1.add("[3] Текущий канал");
                    s1.add("[4] Список каналов");
                    s1.add("[5] Добавить канал");
                    s1.add("[6] Удалить канал");
                    s1.add("[7] Следующий канал");
                    s1.add("[8] Предыдущий канал");
                }
                case dishwasher -> {
                    s1.add("[3] Текущие параметры");
                    s1.add("[4] Задать режим");
                    s1.add("[5] Задать t° воды");
                    if(((dishwasher)(smart_devices.get(listBox.getSelectionModel().getSelectedIndex()))).isChild_protection())
                        s1.add("[6] Выкл. защиту от детей");
                    else
                        s1.add("[6] Вкл. защиту от детей");
                }
            }
            ObservableList<String> langs = FXCollections.observableArrayList(s1);
            operationList.setItems(langs);
            return true;
    }

    void choiceSelect(int _choice){
        if(_choice==-1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка выполнения...");
            alert.setHeaderText("Ошибка выбора функции");
            alert.showAndWait().ifPresent(rs -> {
            });
        }
        else {
            TextState.clear();
            _choice++;
            smart_devices devices=smart_devices.get(listBox.getSelectionModel().getSelectedIndex());
            if((_choice)==1) {
                changeName(devices);
            }
            else if((_choice)==2) {
                devices.changeState();
                RefreshOper();
            }
            else otherFun(devices,(_choice));
        }
    }

    void changeName(smart_devices _dev) {
            String newName=DialogInput("Введите новое имя устройства");
            if(newName!="") _dev.setName(newName);
            int index = listBox.getSelectionModel().getSelectedIndex();
            ShowDev();
            listBox.getSelectionModel().selectIndices(index);
    }

    boolean showRoom(smart_devices _dev){
        int i=1;
        TextState.appendText("Комнаты :\n");
        if(((robot_vacuum_cleaner)_dev).getRooms().size()==0) {
            TextState.appendText("Комнат не существует");
            return false;
        }
        else {
            for (String s : ((robot_vacuum_cleaner) _dev).getRooms()) {
                TextState.appendText(i++ + ") " + s+"\n");
            }
            return true;
        }
    }

    boolean showChannel(smart_devices _dev){
        int i=1;
        TextState.appendText("Каналы :\n");
        if(((tv) _dev).getChannels().size()==0) {
            TextState.appendText("каналов нет");
            return false;
        }
        else {
            for (String s : ((tv) _dev).getChannels()) {
                TextState.appendText(i++ + ") " + s+"\n");
            }
            return true;
        }
    }

    void otherFun(com.vlad.smart_devices _dev, int _menu) {
        String newValue="";
        switch (_dev.getTypeDevices()){
            case robot_vacuum_cleaner -> {
                switch (_menu){
                    case 3:
                        showRoom(_dev);
                        break;
                    case 4:
                        newValue=DialogInput("Введите название комнаты\n(для отмены оставте поле пустым)");
                        if(newValue!="") ((robot_vacuum_cleaner)_dev).addRoom(newValue);
                        break;
                    case 5:
                        if(showRoom(_dev)){
                            newValue=DialogInput("Введите номер комнаты для удаления\n(0 для отмены)");
                            TextState.clear();
                            try {
                                int r=0;
                                if(newValue!="") {
                                    r=Integer.parseInt(newValue);
                                    if(r==0) break;
                                    if(((robot_vacuum_cleaner)_dev).deleteRoom(r-1)) TextState.appendText("Комната удалена");
                                    else TextState.appendText("Неудалось удалить комнату");
                                }
                            }
                            catch (Exception e){
                                TextState.appendText("Ошибка ввода");
                            }
                        }
                        break;
                    case 6:
                        if(!_dev.isState()) {
                            TextState.appendText("Не удалось начать уборку : Устройство выключено");
                            break;
                        }
                        if(((robot_vacuum_cleaner)_dev).getRooms().size()==0) {
                            TextState.appendText("Не удалось начать уборку : Нету комнат");
                            break;
                        }
                        showRoom(_dev);
                        newValue=DialogInput("Введите номер комнаты\n(для отмены 0)");
                        TextState.clear();
                        try {
                            int r=0;
                            if(newValue!="") {
                                r=Integer.parseInt(newValue);
                                if(r==0) break;
                                 if((r-1)>=0 && (r-1)<((robot_vacuum_cleaner)_dev).getRooms().size()) TextState.appendText("Комната "+((robot_vacuum_cleaner) _dev).getRooms().get(r-1)+" убрана");
                                else TextState.appendText("Ошибка выбора комнаты");
                            }
                        }
                        catch (Exception e){
                            TextState.appendText("Ошибка ввода");
                        }
                        break;
                }
            }
            case air_conditioning -> {
                air_conditioning air_c=((air_conditioning)_dev);
                switch (_menu){
                    case 4:
                        air_c.upTemp();
                        break;
                    case 5:
                        air_c.downTemp();
                        break;
                    case 6:
                        newValue=DialogInput("Введите температуру");
                        try {
                            double r=0;
                            if(newValue!="") {
                                r=Double.parseDouble(newValue);  ;
                                air_c.setTemp(r);
                            }
                        }
                        catch (Exception e){
                            TextState.appendText("Ошибка ввода\n");
                        }
                }
                TextState.appendText("Текущая температура : "+air_c.getTemp());
            }

            case heater -> {
                heater _heater=((heater)_dev);
                switch (_menu){
                    case 4:
                        _heater.upTemp();
                        break;
                    case 5:
                        _heater.downTemp();
                        break;
                    case 6:
                        newValue=DialogInput("Введите температуру");
                        try {
                            double r=0;
                            if(newValue!="") {
                                r=Double.parseDouble(newValue);  ;
                                _heater.setTemp(r);
                            }
                        }
                        catch (Exception e){
                            TextState.appendText("Ошибка ввода\n");
                        }
                }
                TextState.appendText("Текущая температура : "+_heater.getTemp());
            }

            case TV -> {
                tv _tv =((tv)_dev);
                switch (_menu){
                    case 3:
                        TextState.appendText("Текущие канал : "+_tv.getChannel());
                        break;
                    case 4:
                        showChannel(_dev);
                        break;
                    case 5:
                        newValue=DialogInput("Введите название канала\n(для отмены оставте поле пустым)");
                        if(newValue!="") _tv.addChannel(newValue);
                        break;
                    case 6:
                        if(showChannel(_dev)){
                            newValue=DialogInput("Введите номер канала для удаления\n(0 для отмены)");
                            TextState.clear();
                            try {
                                int r=0;
                                if(newValue!="") {
                                    r=Integer.parseInt(newValue);
                                    if(r==0) break;
                                    if(_tv.deleteChannel(r-1)) TextState.appendText("Канал удален");
                                    else TextState.appendText("Неудалось удалить канал");
                                }
                            }
                            catch (Exception e){
                                TextState.appendText("Ошибка ввода");
                            }
                        }
                        break;
                    case 7:
                        TextState.appendText(_tv.getChannel() +" -> "+_tv.nextChannel());
                        break;
                    case 8:
                        TextState.appendText(_tv.getChannel() +" -> "+_tv.prevChannel());
                        break;
                }
            }

            case dishwasher -> {
                dishwasher _dishwasher =((dishwasher)_dev);
                switch (_menu){
                    case 3:
                        TextState.appendText("Текущие параметры посудомойки : \n");
                        TextState.appendText("Температура : "+_dishwasher.getTemp()+"\n");
                        TextState.appendText("Режим мойки : "+_dishwasher.getMode()+"\n");
                        TextState.appendText("Защита от детей : "+(_dishwasher.isChild_protection()));
                        break;
                    case 6:
                        _dishwasher.changeChild_protection();
                        ShowDev();
                        if(_dishwasher.isChild_protection())
                            TextState.appendText("Защита от детей включена");
                        else TextState.appendText("Защита от детей выключена");
                        break;
                    case 4:
                        int i =1;
                        TextState.appendText("Режимы работы :\n");
                        for(String mode : _dishwasher.washing_modes){
                            TextState.appendText(i++ + ") " + mode+"\n");
                        }
                        newValue=DialogInput("Введите номер режима\n(для отмены 0)");
                        TextState.clear();
                        try {
                            int r=0;
                            if(newValue!="") {
                                r=Integer.parseInt(newValue);
                                if(r==0) break;
                                if(r>=0 && (r)<_dishwasher.washing_modes.length){
                                    _dishwasher.setMode(r-1);
                                    TextState.appendText("Режим мойки изменен на "+_dishwasher.getMode());
                                }
                                else TextState.appendText("Ошибка выбора режима");
                            }
                        }
                        catch (Exception e){
                            TextState.appendText("Ошибка ввода");
                        }
                        break;
                    case 5:
                        newValue=DialogInput("Введите температуру :\n(40°-70°) ");
                        try {
                            double r=0;
                            if(newValue!="") {
                                r=Double.parseDouble(newValue);
                                _dishwasher.setTemp(r);
                                TextState.appendText("Температура изменена на "+_dishwasher.getTemp()+"°");
                            }
                        }
                        catch (Exception e){
                            TextState.appendText("Ошибка ввода");
                        }
                }
            }
        }
    }

    String DialogInput(String _text){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText(_text);
        dialog.setContentText(_text + " :");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return "";
    }

    void showDisplayDev(boolean par) {
        listBox.setVisible(par);
        operationList.setVisible(par);
        label1.setVisible(par);
        label2.setVisible(par);
        TextState.setVisible(par);
        buttonOperation.setDisable(!par);
        buttonDel.setVisible(par);
    }
}