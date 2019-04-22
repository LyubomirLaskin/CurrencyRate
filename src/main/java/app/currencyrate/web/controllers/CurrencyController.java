package app.currencyrate.web.controllers;

import app.currencyrate.domain.entities.DataModel;
import app.currencyrate.domain.serviceModels.DataModelServiceModel;
import app.currencyrate.services.DataModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@RestController
public class CurrencyController {

    private final DataModelService dataModelService;
    private final ModelMapper modelMapper;

    @Autowired
    public CurrencyController(DataModelService dataModelService, ModelMapper modelMapper) {
        this.dataModelService = dataModelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/currencies")
    Set<String> allCurrencies() throws IOException {

        DataModelServiceModel model = this.dataModelService.getExchangeRates("EUR", "latest");

        return model.getRates().keySet();
    }

    @GetMapping("/rates/latest/{base}")
    DataModel latestRatesModel(@PathVariable String base) throws IOException {

        DataModelServiceModel model = this.dataModelService.getExchangeRates(base, "latest");
        return this.modelMapper.map(model, DataModel.class);

    }

    @GetMapping("/rates/historic/{base}")
    DataModel oldestRatesModel(@PathVariable String base) throws IOException {

        DataModelServiceModel model = this.dataModelService.getExchangeRates(base, "1999-01-01");

        return this.modelMapper.map(model, DataModel.class);
    }

    @GetMapping("/rates/historic/{base}/{date}")
    DataModel historicRatesModel(@PathVariable String base, @PathVariable String date) throws IOException {

        DataModelServiceModel model = this.dataModelService.getExchangeRates(base, date);

        return this.modelMapper.map(model, DataModel.class);
    }

    @GetMapping("/report/{currency}/{startDate}/{endDate}")
    Map<String, BigDecimal> reportForEUR(@PathVariable String currency, @PathVariable String startDate, @PathVariable String endDate) throws IOException {

        return this.dataModelService.getCurrencyRateSeries("EUR", currency, startDate, endDate);

    }
}
