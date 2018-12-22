package com.Service;

import com.Entities.NationCurrency;
import com.Repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("NationServiceImpl")
public class NationServiceImpl implements NationCurrencyService {
    @Autowired
    private NationRepository nationRepository;

    @Override
    public void saveallNationsCurrency() throws IOException {
        String source_url = "https://openexchangerates.org/api/currencies.json";
        URL url = new URL(source_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        Scanner sc = new Scanner(url.openStream());
        while (sc.hasNext()) {
            String inline = sc.nextLine();
            String regex;
            regex = "([(\\s)]*[[\\w][ãíé)-][0-9]])+";
            NationCurrency nation = getNation(inline, regex);
            if (nation.getNationCurrency() != null && nation.getNationDescription() != null) {
                nationRepository.save(nation);
            }
        }
        sc.close();
    }

    @Override
    public List<NationCurrency> getallNationCurrency() {
        return (List<NationCurrency>) nationRepository.findAll();
    }

    private NationCurrency getNation(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        NationCurrency nation = new NationCurrency();
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            nation.setNationCurrency(text.substring(matcher.start(), matcher.end()));
        }
        if (matcher.find()) {
            nation.setNationDescription(text.substring(matcher.start(), matcher.end()));
        }
        return nation;
    }
}
