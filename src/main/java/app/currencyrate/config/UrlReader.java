package app.currencyrate.config;

import java.io.IOException;

public interface UrlReader {

    String readUrl(String requestUrl) throws IOException;
}
