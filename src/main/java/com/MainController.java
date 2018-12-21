package com;

import com.Entities.Boy;
import com.InputForm.FormBoy;
import com.InputForm.FormCurrency;
import com.Entities.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MainController{
    final Logger logger = LoggerFactory.getLogger(Boy.class);
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/request")
    public @ResponseBody Boy post(@RequestBody FormBoy boy){
        Boy newBoy = new Boy(boy.getName(),boy.getEmail());
        userRepository.save(newBoy);
        return newBoy;
    }

    @GetMapping("/requestapi")
    public @ResponseBody Double getCurrency(@RequestBody FormCurrency formCurrency) throws IOException {
        Currency currency = new Currency(formCurrency.From,formCurrency.To);
        return currency.getCurrency();
    }
}