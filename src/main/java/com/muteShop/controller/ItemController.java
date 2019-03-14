package com.muteShop.controller;

import com.muteShop.controller.viewobject.ItemVO;
import com.muteShop.dao.ItemSalesDOMapper;
import com.muteShop.dataobject.ItemSalesDO;
import com.muteShop.error.BusinessException;
import com.muteShop.response.CommonReturnType;
import com.muteShop.service.ItemSalesService;
import com.muteShop.service.ItemService;
import com.muteShop.service.model.ItemModel;
import com.muteShop.service.model.ItemSalesModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.controller
 * @ClassName: ItemController
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-14 下午2:42
 * @Version: 1.0
 */
@Controller("/item")
@RequestMapping("/item")
//跨域
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemSalesService itemSalesService;
    //创建商品
    @RequestMapping(value = "/create" ,method = {RequestMethod.POST} ,consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name ="title")String title,
                                       @RequestParam(name ="imgUrl")String imgUrl,
                                       @RequestParam(name ="stock")Integer stock,
                                       @RequestParam(name ="price")BigDecimal price,
                                       @RequestParam(name ="description")String description
                                       ) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setStock(stock);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        itemModel.setPrice(price);
        ItemModel itemModelForReturn = itemService.createItem(itemModel);
        ItemVO itemVO = this.convertItemVOFromItemModel(itemModelForReturn);
        return CommonReturnType.create(itemVO);
    }

    //商品详情浏览
    @RequestMapping(value = "/get" ,method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id")Integer id){
       ItemModel itemModel = itemService.getItemById(id);
       //拿到商品的销量信息
        ItemSalesModel itemSalesModel =itemSalesService.getItemSalesDOById(id);
        ItemVO itemVO = this.convertItemVOFromItemModel(itemModel,itemSalesModel);
       return CommonReturnType.create(itemVO);
    }
    //商品列表浏览
    @RequestMapping(value = "/listItem" ,method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem(){
        List<ItemModel> itemModelList = itemService.listItem();
        //使用java8 stream API 将list 内 的 itemModel 转化成-----> itemVO
       List<ItemVO> itemVOList =  itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = this.convertItemVOFromItemModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());
       return CommonReturnType.create(itemVOList);
    }

    private ItemVO convertItemVOFromItemModel(ItemModel itemModel,ItemSalesModel itemSalesModel){
        if (itemModel == null || itemSalesModel == null){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        itemVO.setSales(itemSalesModel.getSales());
        return itemVO;
    }
    private ItemVO convertItemVOFromItemModel(ItemModel itemModel){
        if (itemModel == null ){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        return itemVO;
    }

}
