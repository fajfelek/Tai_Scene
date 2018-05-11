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

public class XmlHandler1 extends DefaultHandler{

    private boolean wojFlag = false;
    private boolean powFlag = false;
    private boolean nameFlag = false;
    private boolean gmiFlag = false;
    private boolean rodzFlag = false;
    private boolean idCityFlag = false;
    private boolean idNameFlag = false;
    private boolean cechFlag = false;

    private boolean wojNameFlag = false;

    private String woj;
    private String name;
    private Map<String, String> miasta;
    private Map<String, String> rodzGmi;
    private Map<String, String> wojew;
    private HashMap<String, HashMap<String, String>> outerPowiat;
    private HashMap<String, String> innerPowiat;

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
 * @param name 
 */
    public XmlHandler1(String woj, String name, Map<String, String> miasta, Map<String, String> wojew,
                   Map<String, String> rodzGmi,HashMap<String, HashMap<String, String>> outerPowiat) {
    this.woj = woj;
    this.name = name;
    this.miasta = miasta;
    this.rodzGmi = rodzGmi;
    this.wojew = wojew;
    this.outerPowiat = outerPowiat;
}

    public HashMap<String,String> getInnerPowiat(String key1) {
        HashMap innerMap = outerPowiat.get(key1);
        if (innerMap==null) {
            return null;
        }
        return innerMap;
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
           if(woj.equals(this.woj)){
               WOJ = this.woj;
               wojNameFlag = true;
           } 
       }if (powFlag){
           POW = new String(ch,start,length);
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
       if (wojNameFlag && nameFlag){
           String name = new String(ch, start, length);
           if (name.contains(this.name)){
               counter++;
               NAME = name;
               CITY_NAME = miasta.get(ID_CITY);
               RODZ = rodzGmi.get(RODZ);
               innerPowiat = getInnerPowiat(WOJ);
               POW = innerPowiat.get(POW);
               for (Map.Entry<String, String> entry : wojew.entrySet()) {
                   if (entry.getValue().equals(WOJ)) {
                       WOJ = entry.getKey();
                   }
               }
               streetDescription.add(new Description(WOJ,POW,GMI,RODZ,ID_CITY,ID_NAME,CITY_NAME,CECH,NAME));
           }
           wojNameFlag = false;
       }
   }
    
}

