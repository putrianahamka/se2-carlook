package org.bonn.se.model.objects.entities;

public class Vertriebler extends  User{
    private String kontaktNr;
    private int personalNummer;
    private String vorname;
    private String nachname;
    private String email;
    private String passwort;


    public Vertriebler(){
        super();
        super.setType("v");
        super.setVorname(vorname);
        super.setEmail(email);
        super.setNachname(nachname);
        super.setPasswort(passwort);
    }

    public Vertriebler(int personalNummer, String kontaktNr,String vorname, String nachname, String email,String passwort, String type){
        super.setEmail(email);
        this.kontaktNr= kontaktNr;
        this.personalNummer=personalNummer;
        super.setPasswort(passwort);
        super.setVorname(vorname);
        super.setNachname(nachname);
        super.setPasswort(passwort);
        setType("v");

    }

    public int getPersonalNummer(){
        return personalNummer;
    }
    public void setPersonalNummer(int personalNummer){
        this.personalNummer = personalNummer;
    }
    public String getKontaktNr(){
        return kontaktNr;
    }
    public void setKontaktNr(String kontaktNr){
        this.kontaktNr = kontaktNr;
    }
    public String getVorname(){
        return vorname;
    }
    public void setVorname(String vorname){
        this.vorname=vorname;
    }
    public String getNachname(){
        return nachname;
    }
    public void setNachname(String nachname){
        this.nachname=nachname;
    }

    public void setEmail(String email){
        super.setEmail(email);
    }
    public void setPasswort(String passwort){ super.setPasswort(passwort);}

    @Override
    public String toString(){
        return "" + personalNummer;
    }
}
