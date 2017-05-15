package cisco.controller;

import cisco.xml.XmlConfig;
import cisco.xml.XmlConfigFile;
import cisco.xml.XmlSpeedDialConfigFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;


public class Controller {

//    private ObservableList<String> phoneTypeList = FXCollections.observableArrayList();

    private ObservableList<XmlConfigFile> fileConfigList = FXCollections.observableArrayList();
    private Map<String, XmlConfigFile> defaultConfigList = new TreeMap<>();

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
    private CheckBox chkLang;

    @FXML
    private CheckBox chkNewPhoneType;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private SpeedDialController speedDialController;
    private Stage speedDialStage;

    @FXML
    private void initialize() {

        initDefaultConfigs();
        initExistedConfigs();

        cmbNewPhoneType.setDisable(true);

        txtMac.textProperty().addListener((ov, oldValue, newValue) -> {
            txtMac.setText(newValue.toUpperCase());
        });

        try {

            fxmlLoader.setLocation(getClass().getResource("/fxml/extends.fxml"));
            fxmlEdit = fxmlLoader.load();
            speedDialController = fxmlLoader.getController();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private void initDefaultConfigs() {

        try {
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
            for (File file : folder.listFiles(fileFilter)) {
                try {
                    String[] parts = file.getName().split("_");
                    String[] parts_2 = parts[1].split("\\.");

                    XmlConfigFile xml = new XmlConfigFile(file);
                    xml.setPhoneType(parts_2[0]);

                    if ("7931".equals(parts_2[0])) {
                        defaultConfigList.put(parts_2[0], new XmlSpeedDialConfigFile(file));
                    } else {
                        defaultConfigList.put(parts_2[0], xml);
                    }

//                    defaultFileConfigList.add(new XmlConfigFile(file));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            List<String> comboDefaultList = new ArrayList<String>();
            for (Map.Entry pair : defaultConfigList.entrySet()) {
                comboDefaultList.add((String) pair.getKey());
            }

//            cmbPhoneType = new ComboBox();
            cmbPhoneType.setItems(FXCollections.observableList(comboDefaultList));
            cmbNewPhoneType.setItems(FXCollections.observableList(comboDefaultList));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void initExistedConfigs() {

        try {
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
            fileConfigList = FXCollections.observableArrayList();
            for (File file : folder.listFiles(fileFilter)) {
                try {
                    XmlConfigFile xmlConfigFile = new XmlConfigFile(file);
                    if ("7931".equals(xmlConfigFile.getPhoneType())) {
                        fileConfigList.add(new XmlSpeedDialConfigFile(file));
                    } else {
                        fileConfigList.add(xmlConfigFile);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            List<String> comboExistedList = new ArrayList<String>();
            for (XmlConfigFile xmlConf : fileConfigList) {
                String label;
                if ("6941".equals(xmlConf.getPhoneType())) {
                    label = xmlConf.getFeatureLabel();
                } else {
                    label = xmlConf.getPhoneLabel();
                }
                comboExistedList.add(xmlConf.getUserID() + " : " + label + " : " + xmlConf.getMac());
            }

            Collections.sort(comboExistedList);

//            cmbPhoneNumber = new ComboBox();
            cmbPhoneNumber.setVisibleRowCount(18);
            cmbPhoneNumber.setItems(FXCollections.observableList(comboExistedList));


        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void closeAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void updateAction(ActionEvent actionEvent) {

        // First validation
        // --------------------------
        String strMac = this.txtMac.getText();

        if ("".equals(strMac) || !checkMacField(strMac)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText("Error Dialog");
            alert.setContentText("MAC field is not correct ( " + strMac + " ). Check it again!");

            alert.showAndWait();

            return;
        }

        if (currentSelectedConfig != null) {
            if (chkNewPhoneType.isSelected()) {

            } else {
                updateConfFile(currentSelectedConfig);
                if (currentSelectedConfig instanceof XmlSpeedDialConfigFile) {
                    ((XmlSpeedDialConfigFile) currentSelectedConfig).updateSpeedDialConfig(false);
                } else {
                    currentSelectedConfig.updateConfig(false);
                }
                initDefaultConfigs();
                initExistedConfigs();
                clearTextFields();
            }
            currentSelectedConfig = null;
            currentDefaultConfig = null;
            cmbPhoneType.setValue("select default config");
            cmbPhoneNumber.setValue("select default config");

        } else if (currentDefaultConfig != null) {

            // Second validation
            // ----------------------------
            String fileName = "SEP" + strMac.toUpperCase() + ".cnf.xml";
            File path = new File(XmlConfigFile.getPATH());
            boolean isExists = false;
            boolean isRewrite = false;
            try {
                for (File file : path.listFiles()) {
                    if (fileName.equals(file.getName())) {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                        alert.setTitle("Attention!");
                        alert.setHeaderText("Confirmation Dialog");
                        alert.setContentText("There is another file with the same name: " + fileName + ". Do you want to rewrite this file?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            updateConfFile(currentDefaultConfig);
                            if (currentDefaultConfig instanceof XmlSpeedDialConfigFile) {
                                ((XmlSpeedDialConfigFile) currentDefaultConfig).updateSpeedDialConfig(true);
                            } else {
                                currentDefaultConfig.updateConfig(true);

                            }
                            initDefaultConfigs();
                            initExistedConfigs();
                            clearTextFields();
                            currentDefaultConfig = null;
                            currentSelectedConfig = null;
                            cmbPhoneType.setValue("select default config");
                            cmbPhoneNumber.setValue("select default config");
                        }

                        return;
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            updateConfFile(currentDefaultConfig);
            if (currentDefaultConfig instanceof XmlSpeedDialConfigFile) {
                ((XmlSpeedDialConfigFile) currentDefaultConfig).updateSpeedDialConfig(true);
            } else {
                currentDefaultConfig.updateConfig(true);
            }
            initDefaultConfigs();
            initExistedConfigs();
            clearTextFields();
            currentDefaultConfig = null;
            currentSelectedConfig = null;
            cmbPhoneType.setValue("select default config");
            cmbPhoneNumber.setValue("select default config");

        } else {

        }


    }

    public void cmbPhoneTypeOnShowing(Event event) {

    }

    public void cmbPhoneTypeOnAction(ActionEvent actionEvent) {

        String cmbPhoneTypeStr = (String) this.cmbPhoneType.getValue();
        currentDefaultConfig = defaultConfigList.get(cmbPhoneTypeStr);
        currentSelectedConfig = null;

        updateTextFields(currentDefaultConfig);
        cmbPhoneNumber.setValue("select existing config");
        cmbPhoneNumber.setOpacity(0.5);
        cmbPhoneType.setOpacity(1);
    }

    public void cmbPhoneNumberOnAction(ActionEvent actionEvent) {

        String cmbPhoneNumberStr = (String) this.cmbPhoneNumber.getValue();
        for (XmlConfigFile xmlConf : fileConfigList) {
            if (cmbPhoneNumberStr.contains(xmlConf.getUserID()) &&
                    (cmbPhoneNumberStr.contains(xmlConf.getPhoneLabel()) ||
                            cmbPhoneNumberStr.contains(xmlConf.getFeatureLabel())) &&
                    cmbPhoneNumberStr.contains(xmlConf.getMac())) {
                currentDefaultConfig = null;
                currentSelectedConfig = xmlConf;

                break;
            }
        }

        updateTextFields(currentSelectedConfig);
        cmbPhoneType.setValue("select default config");
        cmbPhoneType.setOpacity(0.5);
        cmbPhoneNumber.setOpacity(1);
    }

    private void updateTextFields(XmlConfigFile xmlConf) {

        txtMac.setText(xmlConf.getMac());
        txtCurrentPhoneType.setText(xmlConf.getPhoneType());
        txtUserId.setText(xmlConf.getUserID());
        txtPassword.setText(xmlConf.getUserPass());
        txtServerAddress.setText(xmlConf.getServerAddress());
        txtNtpAddress.setText(xmlConf.getNtpAddress());

        if ("6941".equals(xmlConf.getPhoneType())) {
            txtPhoneLabel.setText(xmlConf.getFeatureLabel());
        } else {
            txtPhoneLabel.setText(xmlConf.getPhoneLabel());
        }

        txtSipPort.setText(xmlConf.getSipPort());
        txtSipmPort.setText(xmlConf.getSipmPort());
        txtDisplayName.setText(xmlConf.getUserID());
        chkNat.setSelected(xmlConf.isNat());
        chkLang.setSelected(xmlConf.isRussian());

    }

    private void updateConfFile(XmlConfigFile xmlConf) {
        xmlConf.setMac(txtMac.getText());
        xmlConf.setUserID(txtUserId.getText());
        xmlConf.setUserPass(txtPassword.getText());
        xmlConf.setServerAddress(txtServerAddress.getText());
        xmlConf.setNtpAddress(txtNtpAddress.getText());
        xmlConf.setPhoneLabel(txtPhoneLabel.getText());

        if ("6941".equals(xmlConf.getPhoneType())) {
            xmlConf.setFeatureLabel(txtPhoneLabel.getText());
        } else {
            xmlConf.setFeatureLabel(txtUserId.getText());
        }
        xmlConf.setFeatureLabel(txtPhoneLabel.getText());
        xmlConf.setSipPort(txtSipPort.getText());
        xmlConf.setSipmPort(txtSipmPort.getText());
//        xmlConf.setDisplayName(txtDisplayName.getText());
        xmlConf.setNat(chkNat.isSelected());
        xmlConf.setRussian(chkLang.isSelected());


    }

    private void clearTextFields() {

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
        chkLang.setSelected(false);

    }

    public void chkNewPhoneTypeOnAction(ActionEvent actionEvent) {
        if (chkNewPhoneType.isSelected()) {
            cmbNewPhoneType.setDisable(false);
        } else {
            cmbNewPhoneType.setDisable(true);
        }
    }

    public void btnDeleteConfigOnAction(ActionEvent actionEvent) {

        String cmbPhoneNumberStr = (String) this.cmbPhoneNumber.getValue();
        for (XmlConfigFile xmlConf : fileConfigList) {
            if (cmbPhoneNumberStr.contains(xmlConf.getUserID()) &&
                    (cmbPhoneNumberStr.contains(xmlConf.getPhoneLabel()) ||
                            cmbPhoneNumberStr.contains(xmlConf.getFeatureLabel())) &&
                    cmbPhoneNumberStr.contains(xmlConf.getMac())) {


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Attention!");
                alert.setHeaderText("Confirmation Dialog");
                alert.setContentText("Are you sure you want to delete config: " + xmlConf.getFileName().getName() + " ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    currentSelectedConfig.getFileName().delete();
                } else {
                    return;
                }


                break;
            }
        }

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
        } catch (Exception e) {

        }
    }

    public void txtMacOnKeyTyped(KeyEvent keyEvent) {

    }

    public void txtMacOnKeyReleased(KeyEvent keyEvent) {

        String line = this.txtMac.getText();
        boolean isCorrect = checkMacField(line);

        if (isCorrect) {
            this.txtMac.setStyle("-fx-border-color: lawngreen");
        } else {
            this.txtMac.setStyle("-fx-border-color: red");
        }

    }

    private boolean checkMacField(String line) {

        if (line.length() != 12) {
            return false;
        }

        Pattern p = Pattern.compile("[A-F0-9]+");
        if (p.matcher(line).matches()) {
            return true;
        } else {
            return false;
        }

    }

    public void btnSpeedDialOnAction(ActionEvent actionEvent) {

//        if(!(currentSelectedConfig instanceof XmlSpeedDialConfigFile) ||
//                !(currentDefaultConfig instanceof XmlSpeedDialConfigFile)){
//            return;
//        }
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();

        if (speedDialStage == null) {
            speedDialStage = new Stage();
            speedDialStage.setTitle("Speed Dial");
            speedDialStage.setResizable(false);
            speedDialStage.setScene(new Scene(fxmlEdit));
            speedDialStage.initModality(Modality.WINDOW_MODAL);
            speedDialStage.initOwner(parentWindow);
        }

        if (currentDefaultConfig != null && currentDefaultConfig instanceof XmlSpeedDialConfigFile) {
            speedDialController.setXmlSpeedDialConfigFile((XmlSpeedDialConfigFile) currentDefaultConfig);
        } else if (currentSelectedConfig != null && currentSelectedConfig instanceof XmlSpeedDialConfigFile) {
            speedDialController.setXmlSpeedDialConfigFile((XmlSpeedDialConfigFile) currentSelectedConfig);
        } else {
            return;
        }
        speedDialController.updateTextFields();
        speedDialStage.show();

    }

    public void txtMacOnKeyPressed(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {

            String mac = txtMac.getText();
            String number = "";
            String name = "";
            boolean isExists = false;

            for (XmlConfigFile xmlFile : fileConfigList) {
                if (mac.equals(xmlFile.getMac())) {
                    number = xmlFile.getUserID();
                    name = xmlFile.getPhoneLabel();
                    isExists = true;
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information!");
            alert.setHeaderText("Information Dialog");

            if (isExists) {
                Formatter format = new Formatter();
                format.format("Number: %s, Name: %s", number, name);
                alert.setContentText(format.toString());

            } else {
                alert.setContentText("It doesn't exist config with such MAC: " + mac);
            }
            alert.showAndWait();

        }

    }
}
