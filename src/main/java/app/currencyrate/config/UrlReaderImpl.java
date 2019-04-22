package app.currencyrate.config;

import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlReaderImpl implements UrlReader{

    @Override
    public String readUrl(String requestUrl) throws IOException {

        URL url = new URL(requestUrl);

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder result = new StringBuilder();
        String inputLine;

        while((inputLine = in.readLine()) != null){
            result.append(inputLine);
        }
        in.close();

        return result.toString().trim();
    }
}
