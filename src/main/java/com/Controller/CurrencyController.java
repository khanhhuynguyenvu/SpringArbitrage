package com.Controller;

import com.Entities.NationCurrency;
import com.InputForm.FormCurrency;
import com.Service.CurrencyService;
import com.Service.NationService;
import com.TradingProcess.ProfitsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    private NationService nationService;
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/getcurrency")
    public @ResponseBody
    Double getCurrency(@RequestBody FormCurrency formCurrency) throws IOException {
        return currencyService.getCurrency(formCurrency.From,formCurrency.To);
    }

    @GetMapping("/getallcurrency")
    public @ResponseBody
    List<NationCurrency> getallNationCurrency() throws IOException {
        List<NationCurrency> list = nationService.getallNationCurrency();
        if(list.isEmpty()) {
            nationService.saveallNationsCurrency();
            list = nationService.getallNationCurrency();
        }
        return list;
    }

    @GetMapping("/getpathprofits")
    public @ResponseBody
    ArrayList<ProfitsResult> getpathprofits() throws IOException {
        Double getMin_profit = 1.0;
        return currencyService.getPathProfitsResult(getMin_profit);
    }
}
