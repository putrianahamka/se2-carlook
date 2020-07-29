package org.bonn.se.model.objects.dto;

import java.time.LocalDate;
import java.sql.Date;

public class FahrzeugDTO {

    private int id;
    private LocalDate datum;
    private Date zeitstempel;
    private String fahrzeugZustand;
    private String shortDescription;
    private String marke;
    private String modell;
    private String fahrzeugTyp;
    private LocalDate erstzulassung;
    private int preis;
    private int kilometer;
    private int leistung;
    private String kraftstoffart;
    private String getriebe;
    private LocalDate tuev;
    private String aussenfarbe;
    private String anzahlTueren;
    private int anzahlSitzplaetze;
    private String klimaanlage;
    private String fahrzeugart;
    private int anzahlFahrzeughalter;
    private String schadenstoffklasse;
    private String umweltplakette;
    private String description;
    private String garantie;
    private int personalnummer;


    public FahrzeugDTO(){
        setId(id);
        setFahrzeugZustand(fahrzeugZustand);
        setShortDescription(shortDescription);
        setMarke(marke);
        setModell(modell);
        setFahrzeugTyp(fahrzeugTyp);
        setErstzulassung(erstzulassung);
        setPreis(preis);
        setKilometer(kilometer);
        setLeistung(leistung);
        setKraftstoffart(kraftstoffart);
        setGetriebe(getriebe);
        setTuev(tuev);
        setAussenfarbe(aussenfarbe);
        setAnzahlTueren(anzahlTueren);
        setAnzahlSitzplaetze(anzahlSitzplaetze);
        setKlimaanlage(klimaanlage);
        setFahrzeugart(fahrzeugart);
        setAnzahlFahrzeughalter(anzahlFahrzeughalter);
        setSchadenstoffklasse(schadenstoffklasse);
        setUmweltplakette(umweltplakette);
        setDescription(description);
        setGarantie(garantie);
        setZeitstempel(zeitstempel);
        setPersonalnummer(personalnummer);

    }

    public FahrzeugDTO(int id,String fahrzeugZustand,String shortDescription,String marke,String modell,String fahrzeugTyp,
                       LocalDate erstzulassung,int preis,int kilometer,int leistung,String kraftstoffart,String getriebe,LocalDate tuev,
                       String aussenfarbe,String anzahlTueren,int anzahlSitzplaetze,String klimaanlage,String fahrzeugart,int anzahlFahrzeughalter,
                       String schadenstoffklasse,String umweltplakette,String description,String garantie,Date zeitstempel,int personalnummer){
        this.id=id;
        this.fahrzeugZustand=fahrzeugZustand;
        this.shortDescription=shortDescription;
        this.marke=marke;
        this.modell=modell;
        this.fahrzeugTyp=fahrzeugTyp;
        this.erstzulassung=erstzulassung;
        this.preis=preis;
        this.kilometer=kilometer;
        this.leistung=leistung;
        this.kraftstoffart=kraftstoffart;
        this.getriebe=getriebe;
        this.tuev=tuev;
        this.aussenfarbe=aussenfarbe;
        this.anzahlTueren=anzahlTueren;
        this.anzahlSitzplaetze=anzahlSitzplaetze;
        this.klimaanlage=klimaanlage;
        this.fahrzeugart=fahrzeugart;
        this.anzahlFahrzeughalter=anzahlFahrzeughalter;
        this.schadenstoffklasse=schadenstoffklasse;
        this.umweltplakette=umweltplakette;
        this.description=description;
        this.garantie=garantie;
        this.zeitstempel=zeitstempel;
        this.personalnummer=personalnummer;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDatum() {
        return datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    public Date getZeitstempel() {
        return zeitstempel;
    }
    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }
    public String getFahrzeugZustand() {
        return fahrzeugZustand;
    }
    public void setFahrzeugZustand(String fahrzeugZustand) {
        this.fahrzeugZustand = fahrzeugZustand;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getMarke() {
        return marke;
    }
    public void setMarke(String marke) {
        this.marke = marke;
    }
    public String getModell() {
        return modell;
    }
    public void setModell(String modell) {
        this.modell = modell;
    }
    public String getFahrzeugTyp() {
        return fahrzeugTyp;
    }
    public void setFahrzeugTyp(String fahrzeugTyp) {
        this.fahrzeugTyp = fahrzeugTyp;
    }
    public LocalDate getErstzulassung() {
        return erstzulassung;
    }
    public void setErstzulassung(LocalDate erstzulassung) {
        this.erstzulassung = erstzulassung;
    }
    public int getPreis() {
        return preis;
    }
    public void setPreis(int preis) {
        this.preis = preis;
    }
    public int getKilometer() {
        return kilometer;
    }
    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }
    public int getLeistung() {
        return leistung;
    }
    public void setLeistung(int leistung) {
        this.leistung = leistung;
    }
    public String getKraftstoffart() {
        return kraftstoffart;
    }
    public void setKraftstoffart(String kraftstoffart) {
        this.kraftstoffart = kraftstoffart;
    }
    public String getGetriebe() {
        return getriebe;
    }
    public void setGetriebe(String getriebe) {
        this.getriebe = getriebe;
    }
    public LocalDate getTuev() {
        return tuev;
    }
    public void setTuev(LocalDate tuev) {
        this.tuev = tuev;
    }
    public String getAussenfarbe() {
        return aussenfarbe;
    }
    public void setAussenfarbe(String aussenfarbe) {
        this.aussenfarbe = aussenfarbe;
    }
    public String getAnzahlTueren() {
        return anzahlTueren;
    }
    public void setAnzahlTueren(String anzahlTueren) {
        this.anzahlTueren = anzahlTueren;
    }
    public int getAnzahlSitzplaetze() {
        return anzahlSitzplaetze;
    }
    public void setAnzahlSitzplaetze(int anzahlSitzplaetze) {
        this.anzahlSitzplaetze = anzahlSitzplaetze;
    }
    public String getKlimaanlage() {
        return klimaanlage;
    }
    public void setKlimaanlage(String klimaanlage) {
        this.klimaanlage = klimaanlage;
    }
    public String getFahrzeugart() {
        return fahrzeugart;
    }
    public void setFahrzeugart(String fahrzeugart) {
        this.fahrzeugart = fahrzeugart;
    }
    public int getAnzahlFahrzeughalter() {
        return anzahlFahrzeughalter;
    }
    public void setAnzahlFahrzeughalter(int anzahlFahrzeughalter) {
        this.anzahlFahrzeughalter = anzahlFahrzeughalter;
    }
    public String getSchadenstoffklasse() {
        return schadenstoffklasse;
    }
    public void setSchadenstoffklasse(String schadenstoffklasse) {
        this.schadenstoffklasse = schadenstoffklasse;
    }
    public String getUmweltplakette() {
        return umweltplakette;
    }
    public void setUmweltplakette(String umweltplakette) {
        this.umweltplakette = umweltplakette;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getGarantie() {
        return garantie;
    }
    public void setGarantie(String garantie) {
        this.garantie = garantie;
    }
    public int getPersonalnummer() {
        return personalnummer;
    }
    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }





}
