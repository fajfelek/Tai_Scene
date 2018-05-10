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

    Map<String, String> wojew = new HashMap<>();
    Map<String, String> miasta = new HashMap<>();
    Map<String, String> rodzGmi = new HashMap<>();
    HashMap<String, HashMap<String, String>> outerMap;
    HashMap<String, HashMap<String, String>> outerPowiat;
    HashMap<String, String> innerPowiat;

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

        } catch (NoSuchFileException fe) {
            System.err.println("Nie znaleziono pliku SIMC.xml");
        } catch (Exception e) {
            System.err.println("Błąd parsera SIMC");
        }
        createRodzGmiMap();

        innerPowiat = outerPowiat.get("10");
//        for(HashMap.Entry<String, String> entry : innerPowiat.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//                System.out.println(key + " + " + value);
//            }
//        for(HashMap.Entry<String, HashMap<String,String>> entry : outerPowiat.entrySet()) {
//                String key = entry.getKey();
//                HashMap value = entry.getValue();
//                System.out.println(key + " + " + value);
//            }
        //            for(HashMap.Entry<String, HashMap<String,String>> entry : outerPowiat.entrySet()) {
//                String value = entry.get("02").get("InnerKey");
//                System.out.println("Retreived value is : " + value);
//            }


    }
//    private HashMap<String,HashMap<String,String>> createBlankMap(){
//        outerMap.put("02", null);
//        outerMap.put("04", null);
//        outerMap.put("06", null);
//        outerMap.put("08", null);
//        outerMap.put("10", null);
//        outerMap.put("12", null);
//        outerMap.put("14", null);
//        outerMap.put("16", null);
//        outerMap.put("18", null);
//        outerMap.put("20", null);
//        outerMap.put("22", null);
//        outerMap.put("24", null);
//        outerMap.put("26", null);
//        outerMap.put("28", null);
//        outerMap.put("30", null);
//        outerMap.put("32", null);
//
//        return outerMap;
//    }
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
     * @param param
     * @param woj
     * @param pow
     * @param ulic
     * @return
     */

    public Vector<Description> chooser(String param, String woj, String pow, String ulic) {
        System.out.println("DOTARLEM przed try");
        try {
            File stream = new File("src/resources/data/ULIC.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            System.out.println("DOTARLEM po wcytaniu ULIC");

        System.out.println(param + " + " + woj + " + " + pow + " + " + ulic);

        // <WOJ>
        if (param == null && woj != null && pow == null && ulic != null){
            XmlHandler1 userhandler = new XmlHandler1(woj, ulic);
            saxParser.parse(stream, userhandler);
            //return userhandler.getCounter();
        }
        // <WOJ/POW>
        if (param == null && woj != null && pow != null && ulic != null){
            System.out.println("DOTARLEM do IF");
            XmlHandler2 userhandler = new XmlHandler2(woj, pow, ulic, miasta, wojew, rodzGmi);
            System.out.println(stream);
            saxParser.parse(stream, userhandler);
            System.out.println(param + " ++ " + woj + " ++ " + pow + " ++ " + ulic);
            int counter = userhandler.getCounter();

            return userhandler.getStreetDescription();
        }
        // <ulica>
        if (param == null && woj == null && pow == null && ulic != null){
            XmlHandler3 userhandler = new XmlHandler3(ulic);
            saxParser.parse(stream, userhandler);
            //return userhandler.getCounter();
        }
        } catch (Exception e){

        }
        return null;
    }

}
