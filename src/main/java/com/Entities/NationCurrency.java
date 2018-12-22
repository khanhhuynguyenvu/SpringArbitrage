package com.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NationCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String NationCurrency;
    private String NationDescription;

    public NationCurrency() {
    }

    public NationCurrency(String nationCurrency, String nationDescription) {
        NationCurrency = nationCurrency;
        NationDescription = nationDescription;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNationCurrency() {
        return NationCurrency;
    }

    public void setNationCurrency(String nationCurrency) {
        NationCurrency = nationCurrency;
    }

    public String getNationDescription() {
        return NationDescription;
    }

    public void setNationDescription(String nationDescription) {
        NationDescription = nationDescription;
    }
}
