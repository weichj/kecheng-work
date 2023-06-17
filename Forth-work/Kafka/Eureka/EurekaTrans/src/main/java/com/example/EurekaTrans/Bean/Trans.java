package com.example.EurekaTrans.Bean;

public class Trans {
    public int transOrderId;
    public int transId;
    public String origin;
    public String destination;
    public double price;

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTransOrderId() {
        return transOrderId;
    }

    public void setTransOrderId(int transOrderId) {
        this.transOrderId = transOrderId;
    }

}
