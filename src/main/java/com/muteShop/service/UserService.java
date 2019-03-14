package com.muteShop.service;

import com.muteShop.error.BusinessException;
import com.muteShop.service.model.UserModel;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.service
 * @ClassName: UserService
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-11 下午4:39
 * @Version: 1.0
 */
public interface UserService {

    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;

    /**
     * @Method
     * @Author
     * @Version 1.0
     * @Description encrptPassword :用户加密后的密码  telphone 用户的手机号
     * @Return
     * @Exception
     * @Date
     */

    UserModel validateLogin(String encrptPassword,String telphone) throws BusinessException;
}
