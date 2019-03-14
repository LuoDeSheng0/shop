package com.muteShop.controller.viewobject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.controller.viewobject
 * @ClassName: ItemVO
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-14 下午2:43
 * @Version: 1.0
 */
public class ItemVO {

    private Integer id;
    //商品名称

    private String title;

    //商品价格

    private BigDecimal price;
    //库存

    private Integer stock;
    //描述

    private String description;
    //销量
    private Integer sales;
    //图片地址

    private String imgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
