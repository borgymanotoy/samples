package com.samples.rest.springboot.models;

public class ConversionRequest {

    private String fromCurrency;
    private String toCurrency;
    private double sourceAmount;
    private double convertedAmount;

    public ConversionRequest(){}
    public ConversionRequest(String fromCurrency, String toCurrency){
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }
    public ConversionRequest(String fromCurrency, String toCurrency, double sourceAmount){
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.sourceAmount = sourceAmount;
    }
    public ConversionRequest(String fromCurrency, String toCurrency, double sourceAmount, double convertedAmount){
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.sourceAmount = sourceAmount;
        this.convertedAmount = convertedAmount;
    }


    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public boolean isValidRequest(){
        return (null!=fromCurrency && !"".equals(fromCurrency.trim())) && (null!=toCurrency && !"".equals(toCurrency.trim())) && 0 < sourceAmount;
    }
}
