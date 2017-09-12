package com.samples.rest.springboot.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;

public class CurrencyConverter {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            is.close();
        }
        return null;
    }

    public static Double getConversionRate(String fromCurrency, String toCurrency){
        if(null!=fromCurrency && null!=toCurrency){
            try {
                String currencyPair = fromCurrency + "_" + toCurrency;
                String url = "http://free.currencyconverterapi.com/api/v3/convert?q="+currencyPair+"&compact=ultra";
                JSONObject json = readJsonFromUrl(url);
                Double conversion_rate = (Double) json.get(currencyPair);
                if(null!=conversion_rate) return conversion_rate;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public static Double convertCurrency(String fromCurrency, String toCurrency, Double amount){
        if(null!=fromCurrency && null!=toCurrency && null!=amount){
            DecimalFormat df = new DecimalFormat("#.###");
            try {
                String rest_url = "http://api.fixer.io/latest?base=" + fromCurrency;
                JSONObject json = readJsonFromUrl(rest_url);
                JSONObject objRates = (JSONObject) json.get("rates");
                Double conversion = (Double) objRates.get("PHP");
                df.setRoundingMode(RoundingMode.CEILING);

                return Double.valueOf(df.format(conversion)) * amount;
            } catch (IOException e) {
                System.out.println("[Conversion Issue]: Problem converting using fixer.io. Will attempt using currency-converter-api.");
                Double conversion_rate = getConversionRate(fromCurrency, toCurrency);
                if(null!=conversion_rate)
                    return Double.valueOf(df.format(conversion_rate)) * amount;

                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("[USD 133.0 -> PHP]: " + convertCurrency("USD", "PHP", 133.0));
        System.out.println("[PHP 13865.88 -> USD]: " + convertCurrency("PHP", "USD", 13865.88));
        System.out.println("[KRW 133000.00 -> PHP]: " + convertCurrency("KRW", "PHP", 133000.00));
        System.out.println("[QAR 4484 -> PHP]: " + convertCurrency("QAR", "PHP", 4484.0));
        System.out.println("[PHP 30000.0 -> INR]: " + convertCurrency("USD", "PHP", 30000.0));
    }
}
