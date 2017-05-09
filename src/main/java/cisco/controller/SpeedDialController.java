package cisco.controller;


import cisco.xml.XmlConfigFile;
import cisco.xml.XmlSpeedDialConfigFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SpeedDialController {

    XmlSpeedDialConfigFile xmlSpeedDialConfigFile;

    @FXML
    private Button btnSpeedDialSave;

    @FXML
    private Button btnSpeedDialCancel;

    @FXML
    private TextField txtNumber2;
    @FXML
    private TextField txtUser2;

    @FXML
    private TextField txtNumber3;
    @FXML
    private TextField txtUser3;

    @FXML
    private TextField txtNumber4;
    @FXML
    private TextField txtUser4;

    @FXML
    private TextField txtNumber5;
    @FXML
    private TextField txtUser5;

    @FXML
    private TextField txtNumber6;
    @FXML
    private TextField txtUser6;

    @FXML
    private TextField txtNumber7;
    @FXML
    private TextField txtUser7;

    @FXML
    private TextField txtNumber8;
    @FXML
    private TextField txtUser8;

    @FXML
    private TextField txtNumber9;
    @FXML
    private TextField txtUser9;

    @FXML
    private TextField txtNumber10;
    @FXML
    private TextField txtUser10;

    @FXML
    private TextField txtNumber11;
    @FXML
    private TextField txtUser11;

    @FXML
    private TextField txtNumber12;
    @FXML
    private TextField txtUser12;

    @FXML
    private TextField txtNumber13;
    @FXML
    private TextField txtUser13;

    @FXML
    private TextField txtNumber14;
    @FXML
    private TextField txtUser14;

    @FXML
    private TextField txtNumber15;
    @FXML
    private TextField txtUser15;

    @FXML
    private TextField txtNumber16;
    @FXML
    private TextField txtUser16;

    @FXML
    private TextField txtNumber17;
    @FXML
    private TextField txtUser17;

    @FXML
    private TextField txtNumber18;
    @FXML
    private TextField txtUser18;

    @FXML
    private TextField txtNumber19;
    @FXML
    private TextField txtUser19;

    @FXML
    private TextField txtNumber20;
    @FXML
    private TextField txtUser20;

    @FXML
    private TextField txtNumber21;
    @FXML
    private TextField txtUser21;

    @FXML
    private TextField txtNumber22;
    @FXML
    private TextField txtUser22;

    @FXML
    private TextField txtNumber23;
    @FXML
    private TextField txtUser23;

    @FXML
    private TextField txtNumber24;
    @FXML
    private TextField txtUser24;


    public void btnSpeedDialSaveOnAction(ActionEvent actionEvent) {

        updateConfFile(xmlSpeedDialConfigFile);
        Stage stage = (Stage) btnSpeedDialCancel.getScene().getWindow();
        stage.close();
    }

    public void btnSpeedDialCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSpeedDialCancel.getScene().getWindow();
        stage.close();
    }

    public void updateTextFields(XmlSpeedDialConfigFile xmlConf){

        xmlSpeedDialConfigFile = xmlConf;

        txtNumber2.setText(xmlConf.getStrNumber2());
        txtUser2.setText(xmlConf.getStrUser2());

        txtNumber3.setText(xmlConf.getStrNumber3());
        txtUser3.setText(xmlConf.getStrUser3());

    }

    public void updateConfFile(XmlSpeedDialConfigFile xmlConf){

        xmlConf.setStrNumber2(txtNumber2.getText());
        xmlConf.setStrUser2(txtUser2.getText());

        xmlConf.setStrNumber3(txtNumber3.getText());
        xmlConf.setStrUser3(txtUser3.getText());

    }

}
