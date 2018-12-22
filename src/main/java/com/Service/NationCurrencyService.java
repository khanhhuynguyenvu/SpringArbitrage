package com.Service;

import com.Entities.NationCurrency;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("NationCurrencyService")
public interface NationCurrencyService {
    void saveallNationsCurrency() throws IOException;
    List<NationCurrency> getallNationCurrency();
}
