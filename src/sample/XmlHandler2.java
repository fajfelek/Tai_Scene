/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author filip
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class XmlHandler2 extends DefaultHandler {

    private boolean wojFlag = false;
    private boolean powFlag = false;
    private boolean nameFlag = false;
    private boolean gmiFlag = false;
    private boolean rodzFlag = false;
    private boolean idCityFlag = false;
    private boolean idNameFlag = false;
    private boolean cechFlag = false;

    private boolean wojNameFlag = false;
    private boolean powNameFlag = false;

    private String woj;
    private String name;
    private String pow;
    private Map<String, String> miasta;
    private Map<String, String> rodzGmi;
    private Map<String, String> wojew;

    private int counter = 0;

        String WOJ = null;
        String POW = null;
        String GMI = null;
        String RODZ = null;
        String ID_CITY = null;
        String ID_NAME = null;
        String CECH = null;
        String NAME = null;
        String CITY_NAME = null;

    Vector<Description> streetDescription = new Vector<>();


    public Vector<Description> getStreetDescription() {
        return streetDescription;
    }

    /**
 * 
 * @return 
 */
    public int getCounter() {
        return counter;
    }
/**
 * 
 * @param woj
 * @param pow
 * @param name 
 */
    public XmlHandler2(String woj, String pow, String name, Map<String, String> miasta, Map<String, String> wojew,
                       Map<String, String> rodzGmi) {
        this.woj = woj;
        this.name = name;
        this.pow = pow;
        this.miasta = miasta;
        this.rodzGmi = rodzGmi;
        this.wojew = wojew;
    }
/**
 * 
 * @param uri
 * @param localName
 * @param qName
 * @param attributes
 * @throws SAXException 
 */
    @Override
    public void startElement(
            String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("WOJ")) {
            wojFlag = true;
        }
        if (qName.equalsIgnoreCase("POW")) {
            powFlag = true;
        }
        if (qName.equalsIgnoreCase("GMI")) {
            gmiFlag = true;
        }
        if (qName.equalsIgnoreCase("RODZ_GMI")) {
            rodzFlag = true;
        }
        if (qName.equalsIgnoreCase("SYM")) {
            idCityFlag = true;
        }
        if (qName.equalsIgnoreCase("SYM_UL")) {
            idNameFlag = true;
        }
        if (qName.equalsIgnoreCase("CECHA")) {
            cechFlag = true;
        }
        if (qName.equalsIgnoreCase("NAZWA_1")) {
            nameFlag = true;
        }
    }
/**
 * 
 * @param uri
 * @param localName
 * @param qName
 * @throws SAXException 
 */
    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("WOJ")) {
            wojFlag = false;
        }
        if (qName.equalsIgnoreCase("POW")) {
            powFlag = false;
        }
        if (qName.equalsIgnoreCase("GMI")) {
            gmiFlag = false;
        }
        if (qName.equalsIgnoreCase("RODZ_GMI")) {
            rodzFlag = false;
        }
        if (qName.equalsIgnoreCase("SYM")) {
            idCityFlag = false;
        }
        if (qName.equalsIgnoreCase("SYM_UL")) {
            idNameFlag = false;
        }
        if (qName.equalsIgnoreCase("CECHA")) {
            cechFlag = false;
        }
        if (qName.equalsIgnoreCase("NAZWA_1")) {
            nameFlag = false;
        }
    }
/**
 * 
 * @param ch
 * @param start
 * @param length
 * @throws SAXException 
 */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (wojFlag){
            String woj = new String(ch, start, length);
            if (woj.equals(this.woj)){
                wojNameFlag = true;
                WOJ = woj;
            }
        }
        if (wojNameFlag && powFlag){
            String pow = new String(ch, start, length);
            if (pow.equals(this.pow)){
                powNameFlag = true;
                POW = pow;
            }
            wojNameFlag = false;
        }
        if (gmiFlag){
            GMI = new String(ch,start,length);
        }
        if (rodzFlag){
            RODZ = new String(ch,start,length);
        }
        if (idCityFlag){
            ID_CITY= new String(ch,start,length);
        }
        if (idNameFlag){
            ID_NAME = new String(ch,start,length);
        }
        if (cechFlag){
            CECH = new String(ch,start,length);
        }
        if (powNameFlag && nameFlag){
            String name2 = new String(ch, start, length);
            if (name2.contains(this.name)){
                counter++;
                NAME = name2;
                CITY_NAME = miasta.get(ID_CITY);
                RODZ = rodzGmi.get(RODZ);
                for (Map.Entry<String, String> entry : wojew.entrySet()) {
                    if (entry.getValue().equals(WOJ)) {
                        WOJ = entry.getKey();
                    }
                }
                streetDescription.add(new Description(WOJ,POW,GMI,RODZ,ID_CITY,ID_NAME,CITY_NAME,CECH,NAME));
            }
            powNameFlag = false;
        }

    }

}
