package com.st.physiobiometrics.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInClient {

    private String clinic;
    private String client;

    public LoggedInClient(String clinic, String client) {
        this.clinic = clinic;
        this.client = client;
    }

    public String getclinic() {
        return clinic;
    }

    public String getclient() {
        return client;
    }
}