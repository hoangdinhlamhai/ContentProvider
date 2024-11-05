package Model;

import java.io.Serializable;

public class Message implements Serializable {
    private String address;
    private String body;

    public Message(String address, String body) {
        this.address = address;
        this.body = body;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "From: " + address + "\nMessage: " + body;
    }
}