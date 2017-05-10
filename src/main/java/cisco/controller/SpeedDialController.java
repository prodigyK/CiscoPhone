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

    @FXML
    private void intialize() {
        updateTextFields();
    }

    public void btnSpeedDialSaveOnAction(ActionEvent actionEvent) {

        updateConfFile(xmlSpeedDialConfigFile);
        Stage stage = (Stage) btnSpeedDialCancel.getScene().getWindow();
        stage.close();
    }

    public void btnSpeedDialCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSpeedDialCancel.getScene().getWindow();
        stage.close();
    }

    public void updateTextFields() {

        txtNumber2.setText(xmlSpeedDialConfigFile.getStrNumber2());
        txtUser2.setText(xmlSpeedDialConfigFile.getStrUser2());

        txtNumber3.setText(xmlSpeedDialConfigFile.getStrNumber3());
        txtUser3.setText(xmlSpeedDialConfigFile.getStrUser3());

        txtNumber4.setText(xmlSpeedDialConfigFile.getStrNumber4());
        txtUser4.setText(xmlSpeedDialConfigFile.getStrUser4());

        txtNumber5.setText(xmlSpeedDialConfigFile.getStrNumber5());
        txtUser5.setText(xmlSpeedDialConfigFile.getStrUser5());

        txtNumber6.setText(xmlSpeedDialConfigFile.getStrNumber6());
        txtUser6.setText(xmlSpeedDialConfigFile.getStrUser6());

        txtNumber7.setText(xmlSpeedDialConfigFile.getStrNumber7());
        txtUser7.setText(xmlSpeedDialConfigFile.getStrUser7());

        txtNumber8.setText(xmlSpeedDialConfigFile.getStrNumber8());
        txtUser8.setText(xmlSpeedDialConfigFile.getStrUser8());

        txtNumber9.setText(xmlSpeedDialConfigFile.getStrNumber9());
        txtUser9.setText(xmlSpeedDialConfigFile.getStrUser9());

        txtNumber10.setText(xmlSpeedDialConfigFile.getStrNumber10());
        txtUser10.setText(xmlSpeedDialConfigFile.getStrUser10());

        txtNumber11.setText(xmlSpeedDialConfigFile.getStrNumber11());
        txtUser11.setText(xmlSpeedDialConfigFile.getStrUser11());

        txtNumber12.setText(xmlSpeedDialConfigFile.getStrNumber12());
        txtUser12.setText(xmlSpeedDialConfigFile.getStrUser12());

        txtNumber13.setText(xmlSpeedDialConfigFile.getStrNumber13());
        txtUser13.setText(xmlSpeedDialConfigFile.getStrUser13());

        txtNumber14.setText(xmlSpeedDialConfigFile.getStrNumber14());
        txtUser14.setText(xmlSpeedDialConfigFile.getStrUser14());

        txtNumber15.setText(xmlSpeedDialConfigFile.getStrNumber15());
        txtUser15.setText(xmlSpeedDialConfigFile.getStrUser15());

        txtNumber16.setText(xmlSpeedDialConfigFile.getStrNumber16());
        txtUser16.setText(xmlSpeedDialConfigFile.getStrUser16());

        txtNumber17.setText(xmlSpeedDialConfigFile.getStrNumber17());
        txtUser17.setText(xmlSpeedDialConfigFile.getStrUser17());

        txtNumber18.setText(xmlSpeedDialConfigFile.getStrNumber18());
        txtUser18.setText(xmlSpeedDialConfigFile.getStrUser18());

        txtNumber19.setText(xmlSpeedDialConfigFile.getStrNumber19());
        txtUser19.setText(xmlSpeedDialConfigFile.getStrUser19());

        txtNumber20.setText(xmlSpeedDialConfigFile.getStrNumber20());
        txtUser20.setText(xmlSpeedDialConfigFile.getStrUser20());

        txtNumber21.setText(xmlSpeedDialConfigFile.getStrNumber21());
        txtUser21.setText(xmlSpeedDialConfigFile.getStrUser21());

        txtNumber22.setText(xmlSpeedDialConfigFile.getStrNumber22());
        txtUser22.setText(xmlSpeedDialConfigFile.getStrUser22());

        txtNumber23.setText(xmlSpeedDialConfigFile.getStrNumber23());
        txtUser23.setText(xmlSpeedDialConfigFile.getStrUser23());

        txtNumber24.setText(xmlSpeedDialConfigFile.getStrNumber24());
        txtUser24.setText(xmlSpeedDialConfigFile.getStrUser24());

    }

    public void updateConfFile(XmlSpeedDialConfigFile xmlConf) {

        xmlConf.setStrNumber2(txtNumber2.getText());
        xmlConf.setStrUser2(txtUser2.getText());

        xmlConf.setStrNumber3(txtNumber3.getText());
        xmlConf.setStrUser3(txtUser3.getText());

        xmlConf.setStrNumber4(txtNumber4.getText());
        xmlConf.setStrUser4(txtUser4.getText());

        xmlConf.setStrNumber5(txtNumber5.getText());
        xmlConf.setStrUser5(txtUser5.getText());

        xmlConf.setStrNumber6(txtNumber6.getText());
        xmlConf.setStrUser6(txtUser6.getText());

        xmlConf.setStrNumber7(txtNumber7.getText());
        xmlConf.setStrUser7(txtUser7.getText());

        xmlConf.setStrNumber8(txtNumber8.getText());
        xmlConf.setStrUser8(txtUser8.getText());

        xmlConf.setStrNumber9(txtNumber9.getText());
        xmlConf.setStrUser9(txtUser9.getText());

        xmlConf.setStrNumber10(txtNumber10.getText());
        xmlConf.setStrUser10(txtUser10.getText());

        xmlConf.setStrNumber11(txtNumber11.getText());
        xmlConf.setStrUser11(txtUser11.getText());

        xmlConf.setStrNumber12(txtNumber12.getText());
        xmlConf.setStrUser12(txtUser12.getText());

        xmlConf.setStrNumber13(txtNumber13.getText());
        xmlConf.setStrUser13(txtUser13.getText());

        xmlConf.setStrNumber14(txtNumber14.getText());
        xmlConf.setStrUser14(txtUser14.getText());

        xmlConf.setStrNumber15(txtNumber15.getText());
        xmlConf.setStrUser15(txtUser15.getText());

        xmlConf.setStrNumber16(txtNumber16.getText());
        xmlConf.setStrUser16(txtUser16.getText());

        xmlConf.setStrNumber17(txtNumber17.getText());
        xmlConf.setStrUser17(txtUser17.getText());

        xmlConf.setStrNumber18(txtNumber18.getText());
        xmlConf.setStrUser18(txtUser18.getText());

        xmlConf.setStrNumber19(txtNumber19.getText());
        xmlConf.setStrUser19(txtUser19.getText());

        xmlConf.setStrNumber20(txtNumber20.getText());
        xmlConf.setStrUser20(txtUser20.getText());

        xmlConf.setStrNumber21(txtNumber21.getText());
        xmlConf.setStrUser21(txtUser21.getText());

        xmlConf.setStrNumber22(txtNumber22.getText());
        xmlConf.setStrUser22(txtUser22.getText());

        xmlConf.setStrNumber23(txtNumber23.getText());
        xmlConf.setStrUser23(txtUser23.getText());

        xmlConf.setStrNumber24(txtNumber24.getText());
        xmlConf.setStrUser24(txtUser24.getText());

    }

    public void setXmlSpeedDialConfigFile(XmlSpeedDialConfigFile xmlSpeedDialConfigFile) {
        this.xmlSpeedDialConfigFile = xmlSpeedDialConfigFile;
    }
}
