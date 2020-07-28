package org.bonn.se.control.exception;

public class NoSuchUserOrPassword extends Exception {

    public NoSuchUserOrPassword() {
        super("Fehler: Email oder Passwort ist falsch!");
    }
}
