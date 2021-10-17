package pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
public class Product extends BaseEntity{
    @Id
    private Integer id;

    private String name;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "root_category_id")
    private Integer rootCategoryId;

    @Column(name = "chat_method")
    private String chatMethod;

    @Column(name = "product_key")
    private String productKey;

    @Column(name = "product_secret")
    private String productSecret;

    /**
     * 1.低 2.正常 3.高
     */
    @Column(name = "power_dissipation")
    private Short powerDissipation;

    /**
     * 0.不共享 1.共享
     */
    private Short share;

}