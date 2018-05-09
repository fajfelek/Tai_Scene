package sample;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Description {

    private SimpleStringProperty WOJ = new SimpleStringProperty();
    private SimpleStringProperty POW = new SimpleStringProperty();
    private SimpleStringProperty GMI = new SimpleStringProperty();
    private SimpleStringProperty RODZ = new SimpleStringProperty();
    private SimpleStringProperty ID_CITY = new SimpleStringProperty();
    private SimpleStringProperty ID_NAME = new SimpleStringProperty();
    private SimpleStringProperty CECH = new SimpleStringProperty();
    private SimpleStringProperty NAME = new SimpleStringProperty();


    public Description(String woj, String pow, String gmi, String rodz, String id_city, String id_name,
                       String cech, String name){
        this.WOJ.setValue(woj);
        this.POW.setValue(pow);
        this.GMI.setValue(gmi);
        this.RODZ.setValue(rodz);
        this.ID_CITY.setValue(id_city);
        this.ID_NAME.setValue(id_name);
        this.CECH.setValue(cech);
        this.NAME.setValue(name);
    }

    public String getWOJ() {
        return WOJ.get();
    }

    public SimpleStringProperty WOJProperty() {
        return WOJ;
    }

    public void setWOJ(String WOJ) {
        this.WOJ.set(WOJ);
    }

    public String getPOW() {
        return POW.get();
    }

    public SimpleStringProperty POWProperty() {
        return POW;
    }

    public void setPOW(String POW) {
        this.POW.set(POW);
    }

    public String getGMI() {
        return GMI.get();
    }

    public SimpleStringProperty GMIProperty() {
        return GMI;
    }

    public void setGMI(String GMI) {
        this.GMI.set(GMI);
    }

    public String getRODZ() {
        return RODZ.get();
    }

    public SimpleStringProperty RODZProperty() {
        return RODZ;
    }

    public void setRODZ(String RODZ) {
        this.RODZ.set(RODZ);
    }

    public String getID_CITY() {
        return ID_CITY.get();
    }

    public SimpleStringProperty ID_CITYProperty() {
        return ID_CITY;
    }

    public void setID_CITY(String ID_CITY) {
        this.ID_CITY.set(ID_CITY);
    }

    public String getID_NAME() {
        return ID_NAME.get();
    }

    public SimpleStringProperty ID_NAMEProperty() {
        return ID_NAME;
    }

    public void setID_NAME(String ID_NAME) {
        this.ID_NAME.set(ID_NAME);
    }

    public String getCECH() {
        return CECH.get();
    }

    public SimpleStringProperty CECHProperty() {
        return CECH;
    }

    public void setCECH(String CECH) {
        this.CECH.set(CECH);
    }

    public String getNAME() {
        return NAME.get();
    }

    public SimpleStringProperty NAMEProperty() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME.set(NAME);
    }


//    @Override
//    public String toString() {
//        return "Description{" +
//                "WOJ='" + WOJ + '\'' +
//                ", POW='" + POW + '\'' +
//                ", GMI='" + GMI + '\'' +
//                ", RODZ='" + RODZ + '\'' +
//                ", ID_CITY='" + ID_CITY + '\'' +
//                ", ID_NAME='" + ID_NAME + '\'' +
//                ", CECH='" + CECH + '\'' +
//                ", NAME='" + NAME + '\'' +
//                '}';
//    }

}

