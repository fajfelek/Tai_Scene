package sample;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author filip
 */
public class HandlerChooser {

    private Map<String, String> wojew = new HashMap<>();
    private Map<String, String> miasta = new HashMap<>();
    private Map<String, String> rodzGmi = new HashMap<>();
    private HashMap<String, HashMap<String, String>> outerPowiat;
    private int counter;

    public Map<String, String> getWojew() {
        return wojew;
    }
    public Map<String, String> getMiasta() {
        return miasta;
    }
    public HashMap<String, HashMap<String, String>> getOuterPowiat() { return outerPowiat; }

    public HandlerChooser() {

// wypisanie hashMap
//            for(Map.Entry<String, String> entry : wojew.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//                System.out.println(key);// + " + " + value);
//            }
        try {
            File stream = new File("src/resources/data/TERC.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            TERChandler userhandler = new TERChandler();
            saxParser.parse(stream, userhandler);

            wojew = userhandler.getWojew();
            outerPowiat = userhandler.getOuterPowiatMap();

        } catch (NoSuchFileException fe) {
            System.err.println("Nie znaleziono pliku TERC.xml");
        } catch (Exception e) {
            System.err.println("Błąd parsera TERC");
        }

        try {
            File stream = new File("src/resources/data/SIMC.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SIMChandler userhandler = new SIMChandler();
            saxParser.parse(stream, userhandler);

            miasta = userhandler.getMiasta();
//            for(Map.Entry<String, String> entry : miasta.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//                System.out.println(key + " + " + value);
//            }

        } catch (NoSuchFileException fe) {
            System.err.println("Nie znaleziono pliku SIMC.xml");
        } catch (Exception e) {
            System.err.println("Błąd parsera SIMC");
        }
        createRodzGmiMap();


    }

    private void createRodzGmiMap(){
        rodzGmi.put("1", "miejska");
        rodzGmi.put("2", "wiejska");
        rodzGmi.put("3", "miejsko-wiejska");
        rodzGmi.put("4", "miasto w gminie miejsko-wiejskiej");
        rodzGmi.put("5", "obszar wiejski w gminie miejsko-wiejskiej");
        rodzGmi.put("8", "dzielica Warszawy");
        rodzGmi.put("9", "delegatury miast: Kraków, Łódź, Poznań, Wrocław");
    }
    /**
     *
     * @param woj
     * @param pow
     * @param ulic
     * @return
     */

    public Vector<Description> chooser(String woj, String pow, String ulic) {
        System.out.println("DOTARLEM przed try");
        try {
            File stream = new File("src/resources/data/ULIC.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            System.out.println("DOTARLEM po wcytaniu ULIC");

        System.out.println(woj + " + " + pow + " + " + ulic);

        // <WOJ>
        if (woj != null && pow == null && ulic != null){
            XmlHandler1 userhandler = new XmlHandler1(woj, ulic, miasta, wojew, rodzGmi, outerPowiat);
            saxParser.parse(stream, userhandler);
            counter = userhandler.getCounter();
            return userhandler.getStreetDescription();
        }
        // <WOJ/POW>
        if (woj != null && pow != null && ulic != null){
            XmlHandler2 userhandler = new XmlHandler2(woj, pow, ulic, miasta, wojew, rodzGmi, outerPowiat);
            saxParser.parse(stream, userhandler);
            counter = userhandler.getCounter();
            return userhandler.getStreetDescription();
        }
        // <ulica>
        if (woj == null && pow == null && ulic != null){
            XmlHandler3 userhandler = new XmlHandler3(ulic, miasta, wojew, rodzGmi, outerPowiat);
            saxParser.parse(stream, userhandler);
            return userhandler.getStreetDescription();
        }
        } catch (Exception e){

        }
        return null;
    }

}
