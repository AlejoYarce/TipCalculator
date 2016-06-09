package com.alejoyarce.tipcalculator.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TipRecord {
    private double bill;
    private int tipPercentage;
    private Date timeStamp;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getTip() {
        return bill * (tipPercentage / 100d);
    }

    public String getDateFormatted() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return simpleDateFormat.format(timeStamp);
    }
}
