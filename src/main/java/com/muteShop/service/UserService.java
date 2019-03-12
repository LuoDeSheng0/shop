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
}
