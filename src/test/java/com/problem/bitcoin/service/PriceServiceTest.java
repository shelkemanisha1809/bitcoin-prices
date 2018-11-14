package com.problem.bitcoin.service;

import com.problem.bitcoin.dto.CoinBaseResponse;
import com.problem.bitcoin.util.RestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PriceServiceTest {

    @Autowired
    private PriceService priceService;

    @Autowired
    private RestUtil restUtil;

    @Test
    public void test_getData_for_period_equal_to_all() throws IOException {
        CoinBaseResponse response = priceService.getData("all");
        assertThat(response).isNotNull();
        assertThat(response.getData().getBase()).isEqualTo("BTC");
        assertThat(response.getData().getCurrency()).isEqualTo("USD");
        assertThat(response.getData().getPrices().size()).isEqualTo(2143);
    }

    @Test
    public void test_getData_for_period_equal_to_week() throws IOException {
        CoinBaseResponse response = priceService.getData("week");
        assertThat(response).isNotNull();
        assertThat(response.getData().getBase()).isEqualTo("BTC");
        assertThat(response.getData().getCurrency()).isEqualTo("USD");
        assertThat(response.getData().getPrices().size()).isEqualTo(7);
    }

    @Test
    public void test_getData_for_period_equal_to_month() throws IOException {
        CoinBaseResponse response = priceService.getData("month");
        assertThat(response).isNotNull();
        assertThat(response.getData().getBase()).isEqualTo("BTC");
        assertThat(response.getData().getCurrency()).isEqualTo("USD");
        assertThat(response.getData().getPrices().size()).isEqualTo(31);
    }


    @Test
    public void test_getData_for_period_equal_to_year() throws IOException {
        CoinBaseResponse response = priceService.getData("year");
        assertThat(response).isNotNull();
        assertThat(response.getData().getBase()).isEqualTo("BTC");
        assertThat(response.getData().getCurrency()).isEqualTo("USD");
        assertThat(response.getData().getPrices().size()).isEqualTo(365);
    }

    @Test
    public void test_getData_for_customised_dates() throws IOException {
        CoinBaseResponse response = priceService.getDataBasedOnDates("2018-09-16", "2018-11-16");
        assertThat(response).isNotNull();
        assertThat(response.getData().getBase()).isEqualTo("BTC");
        assertThat(response.getData().getCurrency()).isEqualTo("USD");
        assertThat(response.getData().getPrices().size()).isEqualTo(60);
    }
}
