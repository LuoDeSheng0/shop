package com.muteShop.service.impl;

import com.muteShop.dao.ItemDOMapper;
import com.muteShop.dao.ItemSalesDOMapper;
import com.muteShop.dao.ItemStockDOMapper;
import com.muteShop.dataobject.ItemDO;
import com.muteShop.dataobject.ItemSalesDO;
import com.muteShop.dataobject.ItemStockDO;
import com.muteShop.error.BusinessException;
import com.muteShop.error.EmBusinessError;
import com.muteShop.service.ItemService;
import com.muteShop.service.model.ItemModel;
import com.muteShop.validator.ValidationResult;
import com.muteShop.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.service.impl
 * @ClassName: ItemServiceImpl
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-14 下午2:03
 * @Version: 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDOMapper itemDOMapper;
    @Autowired
    private ItemStockDOMapper itemStockDOMapper;
    @Autowired
    private ItemSalesDOMapper itemSalesDOMapper;
    @Autowired
    private ValidatorImpl validator;
    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        //校验入参
       ValidationResult result = validator.validate(itemModel);
        if (result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        //转化itemModel ---> dataobject
        ItemDO itemDO = new ItemDO();
        itemDO = this.covertItemDOFromItemModel(itemModel);


        //写入数据库
        itemDOMapper.insertSelective(itemDO);
        //
        itemModel.setId(itemDO.getId());
        itemModel.setSales(0);
        ItemStockDO itemStockDO = this.convertItemStockFromItemModel(itemModel);
        //写入库存
        itemStockDOMapper.insertSelective(itemStockDO);
        //写入销量 默认0
        ItemSalesDO itemSalesDO = this.convertItemSalesDOFromItemModel(itemModel);
        itemSalesDOMapper.insertSelective(itemSalesDO);
        //返回创建完成的对象
        return this.getItemById(itemDO.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        //java8 兰布达表达式
           List<ItemModel> itemModelList =  itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemSalesDO itemSalesDO = itemSalesDOMapper.selectItemSalesDOByItemId(itemDO.getId());
            ItemModel itemModel = this.convertModelFromDataObject(itemDO,itemStockDO,itemSalesDO);
            return itemModel;
        }).collect(Collectors.toList());
    return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null){
            return null;
        }
        //操作获得库存数量和销量
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
        //将dataobject ------>model
        ItemModel itemModel = this.convertModelFromDataObject(itemDO,itemStockDO);
        return itemModel;
    }
    private ItemModel convertModelFromDataObject(ItemDO itemDO,ItemStockDO itemStockDO){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        itemModel.setStock(itemStockDO.getStock());

        return itemModel;
    }
    private ItemModel convertModelFromDataObject(ItemDO itemDO,ItemStockDO itemStockDO,ItemSalesDO itemSalesDO){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        itemModel.setStock(itemStockDO.getStock());
        itemModel.setSales(itemSalesDO.getSales());

        return itemModel;
    }
    private ItemSalesDO convertItemSalesDOFromItemModel(ItemModel itemModel){
        if (itemModel == null){
            return null;
        }
        ItemSalesDO itemSalesDO = new ItemSalesDO();
        itemSalesDO.setSales(itemModel.getSales());
        itemSalesDO.setItemId(itemModel.getId());
        return itemSalesDO;
    }

    private ItemDO covertItemDOFromItemModel(ItemModel itemModel){
        if (itemModel == null){
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        return itemDO;
    }

    private ItemStockDO convertItemStockFromItemModel(ItemModel itemModel){
        if (itemModel == null){
            return null;
        }
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }
}
