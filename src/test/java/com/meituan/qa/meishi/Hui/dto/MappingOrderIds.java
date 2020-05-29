package com.meituan.qa.meishi.Hui.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MappingOrderIds {
    private String oldOrderId;
    private String newOrderId;

}
