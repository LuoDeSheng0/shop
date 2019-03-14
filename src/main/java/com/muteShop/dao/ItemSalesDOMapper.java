package com.muteShop.dao;

import com.muteShop.dataobject.ItemSalesDO;

public interface ItemSalesDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_sales
     *
     * @mbg.generated Thu Mar 14 12:03:12 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_sales
     *
     * @mbg.generated Thu Mar 14 12:03:12 CST 2019
     */
    int insert(ItemSalesDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_sales
     *
     * @mbg.generated Thu Mar 14 12:03:12 CST 2019
     */
    int insertSelective(ItemSalesDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_sales
     *
     * @mbg.generated Thu Mar 14 12:03:12 CST 2019
     */
    ItemSalesDO selectByPrimaryKey(Integer id);
    ItemSalesDO selectItemSalesDOByItemId(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_sales
     *
     * @mbg.generated Thu Mar 14 12:03:12 CST 2019
     */
    int updateByPrimaryKeySelective(ItemSalesDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_sales
     *
     * @mbg.generated Thu Mar 14 12:03:12 CST 2019
     */
    int updateByPrimaryKey(ItemSalesDO record);
}