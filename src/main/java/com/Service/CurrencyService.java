package com.Service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("CurrencyService")
public interface CurrencyService {
    Double getCurrency(String From,String To) throws IOException;
   // List<List<Integer>> getPairCurrency();
}
