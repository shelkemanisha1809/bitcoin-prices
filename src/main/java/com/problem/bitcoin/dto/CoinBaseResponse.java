package com.problem.bitcoin.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinBaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Data data;


    @lombok.Data
    @NoArgsConstructor
    public class Data {
        private String base;
        private List<Prices> prices;
        private String currency;
    }

    @lombok.Data
    @NoArgsConstructor
    public class Prices {
        private Double price;
        private Date time;
    }
}
