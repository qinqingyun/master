package com.meituan.qa.meishi.Hui.dto;

import lombok.Data;

import java.util.List;

@Data
public class Promogroup {
    private long id;
    private List<Promo> promos;
}
