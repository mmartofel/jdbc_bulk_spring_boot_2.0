package com.example.jdbc_bulk;

import org.json.JSONObject;

public class acmsMessageGP {

    private final int dateInt;
    private JSONObject payloadJSON;

    public acmsMessageGP(int dateInt, JSONObject payloadJSON) {
        this.dateInt = dateInt;
        this.payloadJSON = payloadJSON;
    }

    public int getDateInt() {
        return dateInt;
    }

    public JSONObject getPayloadJSON() {
        return payloadJSON;
    }

    public void setPayloadJSON(JSONObject payloadJSON) {
        this.payloadJSON = payloadJSON;
    }

    @Override
    public String toString() {
        return "acmsMessage [dateInt=" + dateInt + ", payloadJSON=" + payloadJSON.toString() + "]";
    }

}
