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

public class XmlHandler1 extends DefaultHandler{

   boolean wojFlag = false;
   boolean nameFlag = false;
   
   boolean wojNameFlag = false;

   String woj;
   String name;
   
   int counter = 0;
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
    public XmlHandler1(String woj, String name) {
        this.woj = woj;
        this.name = name;
        
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
        if (qName.equalsIgnoreCase("WOJ")) {
          wojFlag = false;  
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
               wojNameFlag = true;
           } 
       }
        if (wojNameFlag && nameFlag){
           String name = new String(ch, start, length);
           if (name.contains(this.name)){
               counter++;
           }
           wojNameFlag = false;
       }

   }
    
}

