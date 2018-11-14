package com.problem.bitcoin.controller;

import com.problem.bitcoin.dto.CoinBaseResponse;
import com.problem.bitcoin.dto.RollingAverage;
import com.problem.bitcoin.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/prices")
@AllArgsConstructor
public class PriceController {

    private final PriceService priceService;

    /**
     * API: /prices?period=all or /prices?period=week or /prices?period=month or /prices?period=year
     *
     * @param period
     * @return
     * @throws IOException
     */
    @GetMapping(value = "")
    public CoinBaseResponse getPrice(@RequestParam String period) throws IOException {
        return priceService.getData(period);
    }

    /**
     * API: /prices/dates?d1=2018-10-16&d2=2018-11-16
     *
     * @param d1
     * @param d2
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/dates")
    public CoinBaseResponse getPriceWithCustomDates(@RequestParam String d1, @RequestParam String d2) throws IOException {
        return priceService.getDataBasedOnDates(d1, d2);
    }

    /**
     * API: /prices/avg?d1=2018-10-19&d2=2018-11-16
     * @param d1
     * @param d2
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/avg")
    public RollingAverage getRollingAverageWithCustomDates(@RequestParam String d1, @RequestParam String d2) throws IOException {
        return priceService.getRollingPriceBasedOnDates(d1, d2);
    }

}
