package com.muteShop.service;

import com.muteShop.dataobject.ItemSalesDO;
import com.muteShop.service.model.ItemSalesModel;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.service
 * @ClassName: ItemSalesService
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-14 下午4:53
 * @Version: 1.0
 */
public interface ItemSalesService {
    ItemSalesModel getItemSalesDOById(Integer itemId);
}
