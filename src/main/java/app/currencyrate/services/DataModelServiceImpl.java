package app.currencyrate.services;

import app.currencyrate.config.UrlReader;
import app.currencyrate.domain.entities.DataModel;
import app.currencyrate.domain.serviceModels.DataModelServiceModel;
import app.currencyrate.repositories.DataModelRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataModelServiceImpl implements DataModelService {

    private final DataModelRepository dataModelRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final UrlReader urlReader;

    @Autowired
    public DataModelServiceImpl(DataModelRepository dataModelRepository, ModelMapper modelMapper, Gson gson, UrlReader urlReader) {
        this.dataModelRepository = dataModelRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.urlReader = urlReader;
    }


    @Override
    public List<DataModelServiceModel> findAll() {
        return this.dataModelRepository.findAll()
                .stream().map(entity -> this.modelMapper.map(entity, DataModelServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void fillDB(String base, String date) throws IOException {

        //Enter your Access Key here
        final String accessKey = "";

        String url = "http://data.fixer.io/api/" + date + "?access_key=" + accessKey + "&base" + base;
        String parsedUrl = this.urlReader.readUrl(url);

        DataModelServiceModel model = gson.fromJson(parsedUrl, DataModelServiceModel.class);
        DataModel dataModel = this.modelMapper.map(model, DataModel.class);

        this.dataModelRepository.saveAndFlush(dataModel);
    }

    @Override
    public DataModelServiceModel getExchangeRates(String base, String date) throws IOException {

        if (date.equals("latest")) {
            date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }

        if (this.dataModelRepository.findByBaseAndDate(base, date).isEmpty()) {
            fillDB(base, date);
        }
        DataModel dataModel = this.dataModelRepository
                .findByBaseAndDate(base, date)
                .orElseThrow(() -> new IllegalArgumentException("Not found!"));

        return this.modelMapper.map(dataModel, DataModelServiceModel.class);
    }

    @Override
    public Map<String, BigDecimal> getCurrencyRateSeries(String base, String currency, String startDate, String endDate) throws IOException {

        LocalDate formatedStartDate = LocalDate.parse(startDate);
        LocalDate formatedEndDate = LocalDate.parse(endDate);
        BigDecimal currencyValue = new BigDecimal(0);
        Map<String, BigDecimal> rateSeries = new TreeMap<>();

        for (LocalDate date = formatedStartDate; date.isBefore(formatedEndDate) ; date = date.plusDays(1)) {

            if (this.dataModelRepository.findByBaseAndDate(base, date.toString()).isEmpty()) {
                fillDB(base, date.toString());
            }
            currencyValue = this.dataModelRepository.findByBaseAndDate(base, date.toString())
                    .orElseThrow(() -> new IllegalArgumentException("Not found!")).getRates().get(currency.toUpperCase());

            rateSeries.putIfAbsent(date.toString(), currencyValue);
        }

        return rateSeries;
    }
}
