package com.sapo.consumer;

import lombok.ToString;

@ToString
public class OrderCreatedExchange {
    private int orderId;
    private int price;
    private String receiverName;
    private String shipper;
    private boolean isVip;

    public OrderCreatedExchange(int orderId, int price, String receiverName, String shipper, boolean isVip) {
        this.orderId = orderId;
        this.price = price;
        this.receiverName = receiverName;
        this.shipper = shipper;
        this.isVip = isVip;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
