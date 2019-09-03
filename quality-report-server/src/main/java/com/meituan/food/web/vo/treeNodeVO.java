package com.meituan.food.web.vo;

import lombok.Data;

import java.util.List;

@Data
public class treeNodeVO {
    private int id;
    private String label;
    private List<treeNodeVO> children;
}
