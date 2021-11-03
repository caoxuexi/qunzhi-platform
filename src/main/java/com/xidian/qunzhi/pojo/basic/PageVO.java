package com.xidian.qunzhi.pojo.basic;

import lombok.Data;

import java.util.List;

@Data
public class PageVO<T> {
    private long total;
    private long page;
    private List<T> list;
}