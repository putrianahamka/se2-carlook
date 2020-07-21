package org.bonn.se.services.db.exception;

public class DatabaseException extends Exception {
    private final String reason;

    public DatabaseException(String reason) {
        this.reason=reason;
    }

    public String getReason() {
        return reason;
    }

}
