package org.bonn.se.model.objects.entities;

import java.io.Serializable;

public class User implements Serializable {
    private String vorname;
    private String nachname;
    private String email;
    private String passwort;
    private String type;
    private String kontaktNr;

    public User(){
        setVorname(null);
        setNachname(this.nachname);
        setEmail(this.email);
        setPasswort(this.passwort);
        setType(this.type);
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPasswort(){
        return passwort;
    }
    public void setPasswort(String passwort){
        this.passwort = passwort;
    }
    public String getVorname(){
        return vorname;
    }
    public void setVorname(String vorname){
        this.vorname = vorname;
    }
    public String getNachname(){
        return nachname;
    }
    public void setNachname(String nachname){
        this.nachname = nachname;
    }


    public String getKontaktNr() {
        return kontaktNr;
    }

    public void setKontaktNr(String kontaktNr) {
        this.kontaktNr = kontaktNr;
    }
}
