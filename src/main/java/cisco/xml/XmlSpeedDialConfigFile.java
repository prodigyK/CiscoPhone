package cisco.xml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlSpeedDialConfigFile extends XmlConfigFile {

    private String strNumber2;
    private String strUser2;

    private String strNumber3;
    private String strUser3;

    private String strNumber4;
    private String strUser4;

    private String strNumber5;
    private String strUser5;

    private String strNumber6;
    private String strUser6;

    private String strNumber7;
    private String strUser7;

    private String strNumber8;
    private String strUser8;

    private String strNumber9;
    private String strUser9;

    private String strNumber10;
    private String strUser10;

    private String strNumber11;
    private String strUser11;

    private String strNumber12;
    private String strUser12;

    private String strNumber13;
    private String strUser13;

    private String strNumber14;
    private String strUser14;

    private String strNumber15;
    private String strUser15;

    private String strNumber16;
    private String strUser16;

    private String strNumber17;
    private String strUser17;

    private String strNumber18;
    private String strUser18;

    private String strNumber19;
    private String strUser19;

    private String strNumber20;
    private String strUser20;

    private String strNumber21;
    private String strUser21;

    private String strNumber22;
    private String strUser22;

    private String strNumber23;
    private String strUser23;

    private String strNumber24;
    private String strUser24;

    public XmlSpeedDialConfigFile(File fileName) {
        super(fileName);
//        loadConfig();
        loadSpeedDialConfig();
    }

    public void loadSpeedDialConfig() {

        try {
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            doc = dBuilder.parse(getFileName());

            NodeList nodeList = doc.getElementsByTagName("line");

            if ("2".equals(nodeList.item(1).getAttributes().getNamedItem("button").getNodeValue())) {
                Element el = (Element) nodeList.item(1);
                setStrNumber2(el.getElementsByTagName("speedDialNumber").item(0).getTextContent());
                setStrUser2(el.getElementsByTagName("featureLabel").item(0).getTextContent());
            }
            if ("3".equals(nodeList.item(2).getAttributes().getNamedItem("button").getNodeValue())) {
                Element el = (Element) nodeList.item(2);
                setStrNumber3(el.getElementsByTagName("speedDialNumber").item(0).getTextContent());
                setStrUser3(el.getElementsByTagName("featureLabel").item(0).getTextContent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void updateSpeedDialConfig(boolean isNewFile) {

        NodeList nodeList = doc.getElementsByTagName("line");

        if("2".equals(nodeList.item(1).getAttributes().getNamedItem("button").getNodeValue())){
            Element el = (Element) nodeList.item(1);
            el.getElementsByTagName("speedDialNumber").item(0).setTextContent(this.strNumber2);
            el.getElementsByTagName("featureLabel").item(0).setTextContent(this.strUser2);
        }
        if("3".equals(nodeList.item(2).getAttributes().getNamedItem("button").getNodeValue())){
            Element el = (Element) nodeList.item(2);
            el.getElementsByTagName("speedDialNumber").item(0).setTextContent(this.strNumber3);
            el.getElementsByTagName("featureLabel").item(0).setTextContent(this.strUser3);
        }


        updateConfig(isNewFile);
    }

    public String getStrNumber2() {
        return strNumber2;
    }

    public void setStrNumber2(String strNumber2) {
        this.strNumber2 = strNumber2;
    }

    public String getStrUser2() {
        return strUser2;
    }

    public void setStrUser2(String strUser2) {
        this.strUser2 = strUser2;
    }

    public String getStrNumber3() {
        return strNumber3;
    }

    public void setStrNumber3(String strNumber3) {
        this.strNumber3 = strNumber3;
    }

    public String getStrUser3() {
        return strUser3;
    }

    public void setStrUser3(String strUser3) {
        this.strUser3 = strUser3;
    }

    public String getStrNumber4() {
        return strNumber4;
    }

    public void setStrNumber4(String strNumber4) {
        this.strNumber4 = strNumber4;
    }

    public String getStrUser4() {
        return strUser4;
    }

    public void setStrUser4(String strUser4) {
        this.strUser4 = strUser4;
    }

    public String getStrNumber5() {
        return strNumber5;
    }

    public void setStrNumber5(String strNumber5) {
        this.strNumber5 = strNumber5;
    }

    public String getStrUser5() {
        return strUser5;
    }

    public void setStrUser5(String strUser5) {
        this.strUser5 = strUser5;
    }

    public String getStrNumber6() {
        return strNumber6;
    }

    public void setStrNumber6(String strNumber6) {
        this.strNumber6 = strNumber6;
    }

    public String getStrUser6() {
        return strUser6;
    }

    public void setStrUser6(String strUser6) {
        this.strUser6 = strUser6;
    }

    public String getStrNumber7() {
        return strNumber7;
    }

    public void setStrNumber7(String strNumber7) {
        this.strNumber7 = strNumber7;
    }

    public String getStrUser7() {
        return strUser7;
    }

    public void setStrUser7(String strUser7) {
        this.strUser7 = strUser7;
    }

    public String getStrNumber8() {
        return strNumber8;
    }

    public void setStrNumber8(String strNumber8) {
        this.strNumber8 = strNumber8;
    }

    public String getStrUser8() {
        return strUser8;
    }

    public void setStrUser8(String strUser8) {
        this.strUser8 = strUser8;
    }

    public String getStrNumber9() {
        return strNumber9;
    }

    public void setStrNumber9(String strNumber9) {
        this.strNumber9 = strNumber9;
    }

    public String getStrUser9() {
        return strUser9;
    }

    public void setStrUser9(String strUser9) {
        this.strUser9 = strUser9;
    }

    public String getStrNumber10() {
        return strNumber10;
    }

    public void setStrNumber10(String strNumber10) {
        this.strNumber10 = strNumber10;
    }

    public String getStrUser10() {
        return strUser10;
    }

    public void setStrUser10(String strUser10) {
        this.strUser10 = strUser10;
    }

    public String getStrNumber11() {
        return strNumber11;
    }

    public void setStrNumber11(String strNumber11) {
        this.strNumber11 = strNumber11;
    }

    public String getStrUser11() {
        return strUser11;
    }

    public void setStrUser11(String strUser11) {
        this.strUser11 = strUser11;
    }

    public String getStrNumber12() {
        return strNumber12;
    }

    public void setStrNumber12(String strNumber12) {
        this.strNumber12 = strNumber12;
    }

    public String getStrUser12() {
        return strUser12;
    }

    public void setStrUser12(String strUser12) {
        this.strUser12 = strUser12;
    }

    public String getStrNumber13() {
        return strNumber13;
    }

    public void setStrNumber13(String strNumber13) {
        this.strNumber13 = strNumber13;
    }

    public String getStrUser13() {
        return strUser13;
    }

    public void setStrUser13(String strUser13) {
        this.strUser13 = strUser13;
    }

    public String getStrNumber14() {
        return strNumber14;
    }

    public void setStrNumber14(String strNumber14) {
        this.strNumber14 = strNumber14;
    }

    public String getStrUser14() {
        return strUser14;
    }

    public void setStrUser14(String strUser14) {
        this.strUser14 = strUser14;
    }

    public String getStrNumber15() {
        return strNumber15;
    }

    public void setStrNumber15(String strNumber15) {
        this.strNumber15 = strNumber15;
    }

    public String getStrUser15() {
        return strUser15;
    }

    public void setStrUser15(String strUser15) {
        this.strUser15 = strUser15;
    }

    public String getStrNumber16() {
        return strNumber16;
    }

    public void setStrNumber16(String strNumber16) {
        this.strNumber16 = strNumber16;
    }

    public String getStrUser16() {
        return strUser16;
    }

    public void setStrUser16(String strUser16) {
        this.strUser16 = strUser16;
    }

    public String getStrNumber17() {
        return strNumber17;
    }

    public void setStrNumber17(String strNumber17) {
        this.strNumber17 = strNumber17;
    }

    public String getStrUser17() {
        return strUser17;
    }

    public void setStrUser17(String strUser17) {
        this.strUser17 = strUser17;
    }

    public String getStrNumber18() {
        return strNumber18;
    }

    public void setStrNumber18(String strNumber18) {
        this.strNumber18 = strNumber18;
    }

    public String getStrUser18() {
        return strUser18;
    }

    public void setStrUser18(String strUser18) {
        this.strUser18 = strUser18;
    }

    public String getStrNumber19() {
        return strNumber19;
    }

    public void setStrNumber19(String strNumber19) {
        this.strNumber19 = strNumber19;
    }

    public String getStrUser19() {
        return strUser19;
    }

    public void setStrUser19(String strUser19) {
        this.strUser19 = strUser19;
    }

    public String getStrNumber20() {
        return strNumber20;
    }

    public void setStrNumber20(String strNumber20) {
        this.strNumber20 = strNumber20;
    }

    public String getStrUser20() {
        return strUser20;
    }

    public void setStrUser20(String strUser20) {
        this.strUser20 = strUser20;
    }

    public String getStrNumber21() {
        return strNumber21;
    }

    public void setStrNumber21(String strNumber21) {
        this.strNumber21 = strNumber21;
    }

    public String getStrUser21() {
        return strUser21;
    }

    public void setStrUser21(String strUser21) {
        this.strUser21 = strUser21;
    }

    public String getStrNumber22() {
        return strNumber22;
    }

    public void setStrNumber22(String strNumber22) {
        this.strNumber22 = strNumber22;
    }

    public String getStrUser22() {
        return strUser22;
    }

    public void setStrUser22(String strUser22) {
        this.strUser22 = strUser22;
    }

    public String getStrNumber23() {
        return strNumber23;
    }

    public void setStrNumber23(String strNumber23) {
        this.strNumber23 = strNumber23;
    }

    public String getStrUser23() {
        return strUser23;
    }

    public void setStrUser23(String strUser23) {
        this.strUser23 = strUser23;
    }

    public String getStrNumber24() {
        return strNumber24;
    }

    public void setStrNumber24(String strNumber24) {
        this.strNumber24 = strNumber24;
    }

    public String getStrUser24() {
        return strUser24;
    }

    public void setStrUser24(String strUser24) {
        this.strUser24 = strUser24;
    }
}
