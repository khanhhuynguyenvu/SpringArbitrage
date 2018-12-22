package com.Controller;

import com.Entities.Currency;
import com.Entities.NationCurrency;
import com.InputForm.FormCurrency;
import com.Service.NationCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    private NationCurrencyService nationCurrencyService;

    @GetMapping("/getcurrency")
    public @ResponseBody
    Double getCurrency(@RequestBody FormCurrency formCurrency) throws IOException {
        Currency currency = new Currency(formCurrency.From,formCurrency.To);
        return currency.getCurrency();
    }

    @GetMapping("/getallcurrency")
    public @ResponseBody
    List<NationCurrency> getallNationCurrency() throws IOException {
        List<NationCurrency> list = nationCurrencyService.getallNationCurrency();
        if(list.isEmpty()) {
            nationCurrencyService.saveallNationsCurrency();
            list = nationCurrencyService.getallNationCurrency();
        }
        return list;
    }
}
