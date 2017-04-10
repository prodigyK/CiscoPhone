package cisco.controller;

import cisco.xml.XmlConfigFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Controller {

    private ObservableList<String> phoneTypeList = FXCollections.observableArrayList();
    private ObservableList<XmlConfigFile> defaultFileConfigList = FXCollections.observableArrayList();
    private ObservableList<XmlConfigFile> fileConfigList = FXCollections.observableArrayList();

    private XmlConfigFile currentDefaultConfig;
    private XmlConfigFile currentSelectedConfig;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDeleteConfig;

    @FXML
    private Button btnOpenConfig;

    @FXML
    private ComboBox cmbPhoneType;

    @FXML
    private ComboBox cmbPhoneNumber;

    @FXML
    private ComboBox cmbNewPhoneType;

    @FXML
    private TextField txtMac;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtServerAddress;

    @FXML
    private TextField txtNtpAddress;

    @FXML
    private TextField txtPhoneLabel;

    @FXML
    private TextField txtSipPort;

    @FXML
    private TextField txtSipmPort;

    @FXML
    private TextField txtDisplayName;

    @FXML
    private TextField txtCurrentPhoneType;

    @FXML
    private CheckBox chkNat;

    @FXML
    private CheckBox chkNewPhoneType;

    @FXML
    private void initialize(){
        initDefaultConfigs();
        initExistedConfigs();

        cmbNewPhoneType.setDisable(true);
    }

    private void initDefaultConfigs(){

        try{
            File folder = new File(XmlConfigFile.getPATH());

            FilenameFilter fileFilter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String lowercaseName = name.toLowerCase();
                    if (lowercaseName.startsWith("default")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            for(File file : folder.listFiles(fileFilter)){
                try{
                    defaultFileConfigList.add(new XmlConfigFile(file));
                }catch (Exception e){
                    System.out.println(e.getStackTrace());
                }
            }
            List<String> comboDefaultList = new ArrayList<String>();
            for(XmlConfigFile xmlConf : defaultFileConfigList){
                comboDefaultList.add(xmlConf.getPhoneType());
            }

            cmbPhoneType.setItems(FXCollections.observableList(comboDefaultList));
            cmbNewPhoneType.setItems(FXCollections.observableList(comboDefaultList));


        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }

    }

    private void initExistedConfigs(){

        try{
            File folder = new File(XmlConfigFile.getPATH());

            FilenameFilter fileFilter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String lowercaseName = name.toLowerCase();
                    if (lowercaseName.startsWith("sep")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            for(File file : folder.listFiles(fileFilter)){
                try{
                    fileConfigList.add(new XmlConfigFile(file));
                }catch (Exception e){
                    System.out.println(e.getStackTrace());
                }
            }
            List<String> comboExistedList = new ArrayList<String>();
            for(XmlConfigFile xmlConf : fileConfigList){
                comboExistedList.add(xmlConf.getUserID() + " : " + xmlConf.getDisplayName() + " : " + xmlConf.getMac());
            }

            Collections.sort(comboExistedList);

            cmbPhoneNumber.setItems(FXCollections.observableList(comboExistedList));


        } catch (Exception e){

        }

    }

    public void closeAction(ActionEvent actionEvent){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void updateAction(ActionEvent actionEvent){

        if(currentSelectedConfig != null){
            if(chkNewPhoneType.isSelected()){

            }else{
                fileConfigList.remove(currentSelectedConfig);
                updateConfFile(currentSelectedConfig);
                currentSelectedConfig.updateConfig(false);
                fileConfigList.add(currentSelectedConfig);


            }
        }else if(currentDefaultConfig != null){

            updateConfFile(currentDefaultConfig);
            currentDefaultConfig.updateConfig(true);
            fileConfigList = FXCollections.observableArrayList();
            initExistedConfigs();

        }else{

        }
        List<String> comboExistedList = new ArrayList<String>();
        for(XmlConfigFile xmlConf : fileConfigList){
            comboExistedList.add(xmlConf.getUserID() + " : " + xmlConf.getDisplayName() + " : " + xmlConf.getMac());
        }
        Collections.sort(comboExistedList);

        cmbPhoneNumber.setItems(FXCollections.observableList(comboExistedList));

        clearTextFields();

    }

    public void cmbPhoneTypeOnShowing(Event event) {
        System.out.println("On Showing");
    }

    public void cmbPhoneTypeOnAction(ActionEvent actionEvent) {

        String cmbPhoneTypeStr = (String)this.cmbPhoneType.getValue();
        for(XmlConfigFile xmlConf : defaultFileConfigList){
            if(cmbPhoneTypeStr.equals(xmlConf.getPhoneType())){
                currentDefaultConfig = xmlConf;
                currentSelectedConfig = null;

                break;
            }
        }

        updateTextFields(currentDefaultConfig);
        cmbPhoneNumber.setPromptText("select existing config");
        cmbPhoneNumber.setOpacity(0.5);
        cmbPhoneType.setOpacity(1);
    }

    public void cmbPhoneNumberOnAction(ActionEvent actionEvent) {

        String cmbPhoneNumberStr = (String)this.cmbPhoneNumber.getValue();
        for(XmlConfigFile xmlConf : fileConfigList){
            if(cmbPhoneNumberStr.contains(xmlConf.getUserID()) &&
                    cmbPhoneNumberStr.contains(xmlConf.getDisplayName()) &&
                    cmbPhoneNumberStr.contains(xmlConf.getMac())){
                currentDefaultConfig = null;
                currentSelectedConfig = xmlConf;

                break;
            }
        }

        updateTextFields(currentSelectedConfig);
        cmbPhoneType.setPromptText("select default config");
        cmbPhoneType.setOpacity(0.5);
        cmbPhoneNumber.setOpacity(1);
    }

    private void updateTextFields(XmlConfigFile xmlConf){

        txtMac.setText(xmlConf.getMac());
        txtCurrentPhoneType.setText(xmlConf.getPhoneType());
        txtUserId.setText(xmlConf.getUserID());
        txtPassword.setText(xmlConf.getUserPass());
        txtServerAddress.setText(xmlConf.getServerAddress());
        txtNtpAddress.setText(xmlConf.getNtpAddress());
        txtPhoneLabel.setText(xmlConf.getPhoneLabel());
        txtSipPort.setText(xmlConf.getSipPort());
        txtSipmPort.setText(xmlConf.getSipmPort());
        txtDisplayName.setText(xmlConf.getDisplayName());
        chkNat.setSelected(xmlConf.isNat());

    }

    private void updateConfFile(XmlConfigFile xmlConf){
        xmlConf.setMac(txtMac.getText());
        xmlConf.setUserID(txtUserId.getText());
        xmlConf.setUserPass(txtPassword.getText());
        xmlConf.setServerAddress(txtServerAddress.getText());
        xmlConf.setNtpAddress(txtNtpAddress.getText());
        xmlConf.setPhoneLabel(txtPhoneLabel.getText());
        xmlConf.setSipPort(txtSipPort.getText());
        xmlConf.setSipmPort(txtSipmPort.getText());
        xmlConf.setDisplayName(txtDisplayName.getText());
        xmlConf.setNat(chkNat.isSelected());

    }

    private void clearTextFields(){

        txtMac.setText("");
        txtCurrentPhoneType.setText("");
        txtUserId.setText("");
        txtPassword.setText("");
        txtServerAddress.setText("");
        txtNtpAddress.setText("");
        txtPhoneLabel.setText("");
        txtSipPort.setText("");
        txtSipmPort.setText("");
        txtDisplayName.setText("");
        chkNat.setSelected(false);

    }

    public void chkNewPhoneTypeOnAction(ActionEvent actionEvent) {
        if(chkNewPhoneType.isSelected()){
            cmbNewPhoneType.setDisable(false);
        }else{
            cmbNewPhoneType.setDisable(true);
        }
    }

    public void btnDeleteConfigOnAction(ActionEvent actionEvent) {

        String cmbPhoneNumberStr = (String)this.cmbPhoneNumber.getValue();
        for(XmlConfigFile xmlConf : fileConfigList){
            if(cmbPhoneNumberStr.contains(xmlConf.getUserID()) &&
                    cmbPhoneNumberStr.contains(xmlConf.getDisplayName()) &&
                    cmbPhoneNumberStr.contains(xmlConf.getMac())){

                currentSelectedConfig.getFileName().delete();

                break;
            }
        }


        fileConfigList = FXCollections.observableArrayList();
        initExistedConfigs();
        clearTextFields();


    }

    public void btnOpenConfigOnAction(ActionEvent actionEvent) {

        try {
            if (currentDefaultConfig != null) {
                Runtime.getRuntime().exec("notepad++ " + currentDefaultConfig.getFileName().getAbsolutePath());
            } else if (currentSelectedConfig != null) {
                Runtime.getRuntime().exec("notepad++ " + currentSelectedConfig.getFileName().getAbsolutePath());
            }
        }catch(Exception e){

        }
    }
}
