package com.problem.bitcoin.service;

import com.problem.bitcoin.dto.CoinBaseResponse;
import com.problem.bitcoin.dto.RollingAverage;
import com.problem.bitcoin.util.RestUtil;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private RestUtil restUtil;

    public CoinBaseResponse getData(String period) throws IOException {
        CoinBaseResponse response = restUtil.getResponse();
        if (period.equalsIgnoreCase("all")) {
            return response;
        }
        List<CoinBaseResponse.Prices> prices = new ArrayList<>();
        Interval interval = new Interval(checkPeriodValue(period), new DateTime(new Date()));

        response.getData().getPrices().forEach(prices1 -> {
            if (interval.contains(new DateTime(prices1.getTime()))) {
                prices.add(prices1);
            }
        });

        CoinBaseResponse.Data data = response.getData();
        data.setPrices(prices);
        response.setData(data);
        return response;
    }

    private DateTime checkPeriodValue(String period) {
        if (period.equalsIgnoreCase("week")) {
            return new DateTime(new Date()).minusWeeks(1);
        } else if (period.equalsIgnoreCase("month")) {
            return new DateTime(new Date()).minusMonths(1);
        } else if (period.equalsIgnoreCase("year")) {
            return new DateTime(new Date()).minusYears(1);
        }
        return new DateTime(new Date());
    }


    public CoinBaseResponse getDataBasedOnDates(String d1, String d2) throws IOException {
        CoinBaseResponse response = restUtil.getResponse();
        DateTime dateTime1 = new DateTime(d1);
        DateTime dateTime2 = new DateTime(d2);
        Interval interval = new Interval(dateTime1, dateTime2);

        List<CoinBaseResponse.Prices> prices = new ArrayList<>();
        for (CoinBaseResponse.Prices p : response.getData().getPrices()) {
            if (interval.contains(new DateTime(p.getTime()))) {
                prices.add(p);
            }
        }

        CoinBaseResponse.Data data = response.getData();
        data.setPrices(prices);
        response.setData(data);

        return response;
    }

    public RollingAverage getRollingPriceBasedOnDates(String d1, String d2) throws IOException {
        CoinBaseResponse response = getDataBasedOnDates(d1, d2);
        DateTime dateTime1 = new DateTime(d1);
        DateTime dateTime2 = new DateTime(d2);

        RollingAverage rollingAverage = new RollingAverage();

        int days = Days.daysBetween(dateTime1.withTimeAtStartOfDay(),
                dateTime2.withTimeAtStartOfDay()).getDays();

        double price = response.getData().getPrices()
                .stream().map(prices -> prices.getPrice()).reduce(0.0, Double::sum);

        double avg = price / days;

        rollingAverage.setAveragePrice(avg);
        return rollingAverage;
    }
}
