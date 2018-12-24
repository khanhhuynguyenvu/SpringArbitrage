package com.TradingProcess;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class ProfitsResult {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer Id;

     private String arrayList_profits;
     private Integer length;
     private Double profit;

     public ProfitsResult() {
     }

     public ProfitsResult(Double profit,Integer Length) {
        this.arrayList_profits = "";
        this.profit = profit;
        this.length = Length;
     }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getArrayList_profits() {
        return arrayList_profits;
    }

    public void setArrayList_profits(String arrayList_profits) {
        this.arrayList_profits = arrayList_profits;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
