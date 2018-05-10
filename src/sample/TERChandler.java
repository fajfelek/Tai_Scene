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

public class TERChandler extends DefaultHandler{

    private boolean wojFlag = false;
    private boolean powFlag = false;
    private boolean nazwaFlag = false;
    private boolean nazwadodFlag = false;

    private Map<String, String> wojew = new HashMap<>();
    private HashMap<String, HashMap<String, String>> outerPowiatMap = new HashMap<String, HashMap<String,String>>();
    private HashMap<String, String>[] innerPowiatMap = new HashMap[16];

    public TERChandler(){
        for (int i = 0; i<16; i++){
            innerPowiatMap[i] = new HashMap<String, String>();
        }
    }


    String woj = null;
    String pow = null;
    String nazwa = null;
/**
 * 
 * @param woj
 * @param kod 
 */
    public void addMap(String woj, String kod)
    {
        wojew.put(woj, kod);
    }
/**
 * 
 * @return 
 */
    public Map<String, String> getWojew() {
        return wojew;
    }

    public HashMap<String, HashMap<String, String>> getOuterPowiatMap() { return outerPowiatMap; }

    /**
 * 
 * @param uri
 * @param localName
 * @param qName
 * @param attributes
 * @throws SAXException 
 */
    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("WOJ")) {
            wojFlag = true;
        }
        if (qName.equalsIgnoreCase("POW")) {
            powFlag = true;
        }
        if (qName.equalsIgnoreCase("NAZWA")) {
            nazwaFlag = true;
        }
        if (qName.equalsIgnoreCase("NAZWA_DOD")) {
            nazwadodFlag = true;
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
        if (qName.equalsIgnoreCase("NAZWA")) {
            nazwaFlag = false;
        }
        if (qName.equalsIgnoreCase("NAZWA_DOD")) {
            nazwadodFlag = false;
        }
    }

    private void createPowiatMap(String woj){

        innerPowiatMap[(Integer.parseInt(woj)/2)-1].put(pow, nazwa.substring(0,1).toUpperCase() +
                nazwa.substring(1).toLowerCase());
        outerPowiatMap.put(woj, innerPowiatMap[(Integer.parseInt(woj)/2)-1]);
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
            this.woj = new String(ch, start, length);
        }
        if (powFlag){
            this.pow = new String(ch, start, length);
        }
        if (nazwaFlag){
            this.nazwa = new String(ch, start, length);
        }
        if (nazwadodFlag){
            String nazwadod = new String(ch, start, length);
            if (nazwadod.equals("województwo")){
                addMap(nazwa,woj);
            }
            if (nazwadod.equals("powiat")){
                    createPowiatMap(woj);
            }
        }
    }
}
