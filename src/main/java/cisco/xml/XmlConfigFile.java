package cisco.xml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class XmlConfigFile {
    private static String PATH = "";

    private File fileName;
    private String phoneType;
    private String mac;
    private String userID;
    private String userPass;
    private String serverAddress;
    private String ntpAddress;
    private String phoneLabel;
    private String sipPort;
    private String sipmPort;
    private String displayName;
    private boolean nat;

    static {
        Properties property = new Properties();

        try {
            property.load(XmlConfigFile.class.getResourceAsStream("/config.properties"));

            PATH = property.getProperty("path.to.read.xml");

        } catch (Exception e) {
            System.err.println("Error: a problem occured while reading property file");
        }

    }

    public XmlConfigFile(File fileName) {
        this.fileName = fileName;
        init();
    }

    private void init(){

        if(!this.fileName.exists()){
            return;
        }

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.fileName);

            NodeList loadInformation = doc.getElementsByTagName("loadInformation");
            Element el = (Element) loadInformation.item(0);
            this.phoneType = el.getTextContent();

            NodeList authName = doc.getElementsByTagName("authName");
            el = (Element) authName.item(0);
            this.userID = el.getTextContent();

            NodeList authPassword = doc.getElementsByTagName("authPassword");
            el = (Element) authPassword.item(0);
            this.userPass = el.getTextContent();

            NodeList proxy = doc.getElementsByTagName("proxy");
            el = (Element) proxy.item(0);
            this.serverAddress = el.getTextContent();

            NodeList ntp = doc.getElementsByTagName("ntp");
            el = (Element) ntp.item(0);
            this.ntpAddress = el.getElementsByTagName("name").item(0).getTextContent();

            NodeList phoneLabel = doc.getElementsByTagName("phoneLabel");
            el = (Element) phoneLabel.item(0);
            this.phoneLabel = el.getTextContent();

            NodeList sipPort = doc.getElementsByTagName("sipPort");
            el = (Element) sipPort.item(0);
            this.sipPort = el.getTextContent();

            NodeList sipmPort = doc.getElementsByTagName("securedSipPort");
            el = (Element) sipmPort.item(0);
            this.sipmPort = el.getTextContent();

            NodeList nat = doc.getElementsByTagName("natEnabled");
            el = (Element) nat.item(0);
            this.nat = "true".equals(el.getTextContent());

            NodeList displayName = doc.getElementsByTagName("displayName");
            el = (Element) displayName.item(0);
            this.displayName = el.getTextContent();

            if(this.fileName.getName().startsWith("SEP")){
                this.mac = this.fileName.getName().substring(3,15);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void updateConfig(boolean isNewFile){

        if(!this.fileName.exists()){
            return;
        }

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.fileName);

            NodeList authName = doc.getElementsByTagName("authName");
            Node node = authName.item(0);
            node.setTextContent(this.userID);

            NodeList line = doc.getElementsByTagName("line");
            Element el = (Element) line.item(0);
            el.getElementsByTagName("name").item(0).setTextContent(this.userID);
            el.getElementsByTagName("featureLabel").item(0).setTextContent(this.userID);

            NodeList authPassword = doc.getElementsByTagName("authPassword");
            node = authPassword.item(0);
            node.setTextContent(this.userPass);

            NodeList proxy = doc.getElementsByTagName("proxy");
            node = proxy.item(0);
            node.setTextContent(this.serverAddress);

            NodeList processNodeName = doc.getElementsByTagName("processNodeName");
            node = processNodeName.item(0);
            node.setTextContent(this.serverAddress);

            NodeList ntp = doc.getElementsByTagName("ntp");
            el = (Element) ntp.item(0);
            el.getElementsByTagName("name").item(0).setTextContent(this.ntpAddress);

            NodeList phoneLabel = doc.getElementsByTagName("phoneLabel");
            node = phoneLabel.item(0);
            node.setTextContent(this.phoneLabel);

            NodeList sipPort = doc.getElementsByTagName("sipPort");
            node = sipPort.item(0);
            node.setTextContent(this.sipPort);

            NodeList sipmPort = doc.getElementsByTagName("securedSipPort");
            node = sipmPort.item(0);
            node.setTextContent(this.sipmPort);

            NodeList nat = doc.getElementsByTagName("natEnabled");
            node = nat.item(0);
            node.setTextContent(this.nat ? "true" : "false");

            NodeList displayName = doc.getElementsByTagName("displayName");
            node = displayName.item(0);
            node.setTextContent(this.displayName);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer;
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(isNewFile ? new File(PATH + "SEP" + this.mac.toUpperCase() + ".cnf.xml") : this.fileName);
            transformer.transform(source, result);

        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }

    }

    public static String getPATH() {
        return PATH;
    }

    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getNtpAddress() {
        return ntpAddress;
    }

    public void setNtpAddress(String ntpAddress) {
        this.ntpAddress = ntpAddress;
    }

    public String getPhoneLabel() {
        return phoneLabel;
    }

    public void setPhoneLabel(String phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    public String getSipPort() {
        return sipPort;
    }

    public void setSipPort(String sipPort) {
        this.sipPort = sipPort;
    }

    public String getSipmPort() {
        return sipmPort;
    }

    public void setSipmPort(String sipmPort) {
        this.sipmPort = sipmPort;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isNat() {
        return nat;
    }

    public void setNat(boolean nat) {
        this.nat = nat;
    }
}
