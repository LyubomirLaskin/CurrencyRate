package app.currencyrate.services;

import app.currencyrate.domain.serviceModels.DataModelServiceModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DataModelService {

    List<DataModelServiceModel>findAll();

    void fillDB(String base, String date) throws IOException;

    DataModelServiceModel getExchangeRates(String base, String date) throws IOException;

    Map<String, BigDecimal> getCurrencyRateSeries(String base, String currency, String startDate, String endDate) throws IOException;

}
