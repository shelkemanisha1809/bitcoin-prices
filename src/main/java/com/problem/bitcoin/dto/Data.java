package com.problem.bitcoin.dto;

import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
public class Data {

    private String base;
    private List<Prices> prices;
    private String currency;
}
