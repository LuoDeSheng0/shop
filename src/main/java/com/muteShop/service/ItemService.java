package com.muteShop.service;

import com.muteShop.error.BusinessException;
import com.muteShop.service.model.ItemModel;

import java.util.List;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.service
 * @ClassName: ItemService
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-14 下午2:00
 * @Version: 1.0
 */
public interface ItemService {

    //商品创建
    ItemModel createItem(ItemModel itemModel) throws BusinessException;
    //商品列表浏览
    List<ItemModel> listItem();
    //商品详情浏览
    ItemModel getItemById(Integer id);
}
