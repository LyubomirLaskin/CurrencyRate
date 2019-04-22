package app.currencyrate.domain.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Entity(name = "data_model")
public class DataModel extends BaseEntity {

    private String base;
    private String date;
    private Map<String, BigDecimal> rates;

    public DataModel() {
    }

    @Column(name = "base")
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ElementCollection
    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }
}
