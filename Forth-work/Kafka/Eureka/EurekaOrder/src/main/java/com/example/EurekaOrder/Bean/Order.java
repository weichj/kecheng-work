package com.example.EurekaOrder.Bean;

public class Order {
    public Integer Num;
    public String Shipper;
    public String Origin;
    public String Destination;
    public String GoodsName;
    public String Carriers;

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String transporter;

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer num) {
        Num = num;
    }

    public String getShipper() {
        return Shipper;
    }

    public void setShipper(String shipper) {
        Shipper = shipper;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getCarriers() {
        return Carriers;
    }

    public void setCarriers(String carriers) {
        Carriers = carriers;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String State;

}
