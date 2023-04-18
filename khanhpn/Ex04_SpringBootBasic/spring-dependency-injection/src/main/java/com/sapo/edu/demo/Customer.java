package com.sapo.edu.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;


public class Customer {
    private String acctNo;
    private String pin;
    private BigDecimal balance;

    public Customer() {
    }

    public Customer(String accountNo, String pin, BigDecimal balance) {
        this.acctNo = accountNo;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer [" +
                "Account Number: " + acctNo +
                ", Pin: " + pin +
                ", Balance: " + balance +
                "]";
    }
}
