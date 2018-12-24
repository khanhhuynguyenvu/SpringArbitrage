package com.Service;

import com.TradingProcess.ProfitsResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Service("CurrencyService")
public interface CurrencyService {
    Double getCurrency(String From,String To) throws IOException;
    Double[][] getPairCurrencyRate() throws IOException;
    Map<Integer,String> getMapNationName();
    ArrayList<ProfitsResult> getPathProfitsResult(Double Min_profit) throws IOException;
}
