package com.muteShop.service.impl;

import com.muteShop.dao.ItemSalesDOMapper;
import com.muteShop.dataobject.ItemSalesDO;
import com.muteShop.service.ItemSalesService;
import com.muteShop.service.model.ItemSalesModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.service.impl
 * @ClassName: ItemSalesServiceImpl
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-14 下午4:57
 * @Version: 1.0
 */
@Service
public class ItemSalesServiceImpl implements ItemSalesService {
    @Autowired
    private ItemSalesDOMapper itemSalesDOMapper;
    @Override
    public ItemSalesModel getItemSalesDOById(Integer itemId) {
        ItemSalesDO itemSalesDO = itemSalesDOMapper.selectItemSalesDOByItemId(itemId);
        return this.convertItemSalesModelFromDO(itemSalesDO);
    }

    private ItemSalesModel convertItemSalesModelFromDO(ItemSalesDO itemSalesDO){
        if (itemSalesDO == null){
            return null;
        }
        ItemSalesModel itemSalesModel = new ItemSalesModel();
        BeanUtils.copyProperties(itemSalesDO,itemSalesModel);
        return itemSalesModel;
    }
}
