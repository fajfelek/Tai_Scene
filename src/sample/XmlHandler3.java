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

public class XmlHandler3 extends DefaultHandler{

    boolean nameFlag = false;

    String name;

    int counter = 0;
/**
 * 
 * @param name 
 */
    public XmlHandler3(String name) {
        this.name = name;
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

        if (nameFlag){
            String name = new String(ch, start, length);
            if (name.contains(this.name)){
                counter++;
            }
        }

    }

}

