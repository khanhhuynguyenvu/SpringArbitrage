package com.Service;

import com.Entities.NationCurrency;
import com.TradingProcess.Floyd;
import com.TradingProcess.ProfitsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("CurrencyServiceImpl")
public class CurrencyServiceImpl implements CurrencyService {
    final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Autowired
    NationService nationService;

    @Override
    public Double getCurrency(String From, String To) throws IOException {
        if(From.equals(To)){
            return 1.0;
        }
        logger.info("https request");
        String source_url="https://free.currencyconverterapi.com/api/v6/convert?q="+From+"_"+To+"&compact=ultra";
        /*From = "AFN";
        To = "ALL";
        String source_url="https://free.currencyconverterapi.com/api/v6/convert?q="+From+"_"+To+"&compact=ultra";*/
        URL url = new URL(source_url);
        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        Scanner sc = new Scanner(url.openStream());
        String inline = sc.nextLine();
        logger.info(inline);
        logger.info("done!");
        sc.close();
        return  getValue(inline);
    }

    @Override
    public Double[][] getPairCurrencyRate() throws IOException {
        List<NationCurrency> arrayList = nationService.getallNationCurrency().subList(0,20);
        //List<NationCurrency> arrayList = nationService.getallNationCurrency();
        Double[][] Matrix_currency = new Double[175][175];
        for(NationCurrency i:arrayList){
            for(NationCurrency j:arrayList){
                Double getCurrency = getCurrency(i.getNationCurrency(),j.getNationCurrency());
                Matrix_currency[i.getId()-1][j.getId()-1] = getCurrency;
            }
        }
        return Matrix_currency;
    }

    @Override
    public Map<Integer, String> getMapNationName(){
        Map<Integer,String> getName = new HashMap<>();
        if(nationService.getallNationCurrency().isEmpty()){
            return  null;
        }
        for(NationCurrency i:nationService.getallNationCurrency()){
            getName.put(i.getId()-1,i.getNationCurrency());
        }
        return getName;
    }

    @Override
    public ArrayList<ProfitsResult> getPathProfitsResult(Double Min_profit) throws IOException {
        if (nationService.getallNationCurrency().isEmpty()){
            nationService.saveallNationsCurrency();
        }
        Floyd getFolyd = new Floyd(Min_profit,getPairCurrencyRate(),getMapNationName());
        return getFolyd.getPathProfits();
    }


    private Double getValue(String text){
        String regex = "([0-9]+([.][0-9]+)*)";
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
