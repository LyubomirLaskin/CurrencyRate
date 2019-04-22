package app.currencyrate.domain.serviceModels;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class DataModelServiceModel {

    private String id;
    private String base;
    private String date;
    private Map<String, String> rates;

    public DataModelServiceModel() {
        this.rates = new TreeMap<>();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }
}
