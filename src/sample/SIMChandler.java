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

public class SIMChandler extends DefaultHandler{

    boolean nazwaFlag = false;
    boolean symFlag = false;
    boolean symPodFlag = false;

    Map<String, String> miasta = new HashMap<>();

    String sym = null;
    String nazwa = null;
    String symPod = null;
    /**
     *
     * @param city
     * @param kod
     */
    public void addMap(String city, String kod)
    {
        miasta.put(kod, city);
    }
    /**
     *
     * @return
     */
    public Map<String, String> getMiasta() {
        return miasta;
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
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("NAZWA")) {
            nazwaFlag = true;
        }
        if (qName.equalsIgnoreCase("SYM")) {
            symFlag = true;
        }
        if (qName.equalsIgnoreCase("SYMPOD")) {
            symPodFlag = true;
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

        if (qName.equalsIgnoreCase("NAZWA")) {
            nazwaFlag = false;
        }
        if (qName.equalsIgnoreCase("SYM")) {
            symFlag = false;
        }
        if (qName.equalsIgnoreCase("SYMPOD")) {
            symPodFlag = false;
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

            if (nazwaFlag){
                this.nazwa = new String(ch, start, length);
            }
            if (symFlag){
                this.sym = new String(ch, start, length);
                addMap(nazwa,sym);
            }

    }
}

