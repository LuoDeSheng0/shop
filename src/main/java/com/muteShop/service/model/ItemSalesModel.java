package com.muteShop.service.model;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.service.model
 * @ClassName: ItemSalesModel
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-14 下午5:01
 * @Version: 1.0
 */
public class ItemSalesModel {

    private Integer id;

    private Integer sales;

    private Integer itemId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
