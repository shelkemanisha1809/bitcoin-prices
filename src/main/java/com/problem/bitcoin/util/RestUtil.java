package com.problem.bitcoin.util;

import com.google.gson.Gson;
import com.problem.bitcoin.dto.CoinBaseResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class RestUtil {

    public CoinBaseResponse getResponse() throws IOException {
        URL url = new URL("https://www.coinbase.com/api/v2/prices/BTC-USD/historic?period=all");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            return new Gson().fromJson(output, CoinBaseResponse.class);
        }

        conn.disconnect();

        return null;
    }

}
