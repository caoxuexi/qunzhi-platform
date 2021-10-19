package com.xidian.qunzhi.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Cao Study
 * @description <h1>ProductDetailVO</h1>
 * @date 2021-10-19 11:27
 */
@Data
public class ProductDetailVO {
    private Integer id;

    private String name;

    private String category;

    private String chatMethod;

    private String productKey;

    private String productSecret;

    /**
     * 1.低 2.正常 3.高
     */
    private Short powerDissipation;

    /**
     * 0.不共享 1.共享
     */
    private Short share;

    private Date createTime;

    private Date updateTime;

}
