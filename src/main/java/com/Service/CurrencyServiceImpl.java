package com.Service;

import com.Entities.NationCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("CurrencyServiceImpl")
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    NationService nationService;
    @Override
    public Double getCurrency(String From, String To) throws IOException {
        String source_url="https://free.currencyconverterapi.com/api/v6/convert?q="+From+"_"+To+"&compact=ultra";
        URL url = new URL(source_url);
        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        Scanner sc = new Scanner(url.openStream());
        String inline = sc.nextLine();
        sc.close();
        return  getValue(inline);
    }

   /* @Override
    public List<List<Integer>> getPairCurrency() {
        List<NationCurrency> list = nationService.getallNationCurrency();
        for(NationCurrency nt_1:list){
            for (NationCurrency nt_2:list){
                if(!nt_1.getId().equals(nt_2.getId())){

                }
            }
        }
    }*/

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
