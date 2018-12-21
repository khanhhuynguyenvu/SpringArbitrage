package com.Entities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Currency {
    public Double currency;

    public Double getCurrency() {
        return currency;
    }

    public Currency(String From,String To) throws IOException {
        String source_url="https://free.currencyconverterapi.com/api/v6/convert?q="+From+"_"+To+"&compact=ultra";
        URL url = new URL(source_url);
        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        //String ResponseCode = String.valueOf(conn.getResponseCode());
        Scanner sc = new Scanner(url.openStream());
        String inline = sc.nextLine();
        sc.close();
        this.currency = getValue(inline);
    }
    private Double getValue(String text){
        String regex = "([0-9]+[.][0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        Double res = new Double("0");
        if(matcher.find()) {
            String ParsingText = text.substring(matcher.start(),matcher.end());
            res = Double.parseDouble(ParsingText);
        }
    return res;
    }
}