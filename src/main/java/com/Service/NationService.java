package com.Service;

import com.Entities.NationCurrency;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service("NationService")
public interface NationService {
    void saveallNationsCurrency() throws IOException;
    ArrayList<NationCurrency> getallNationCurrency();
}
