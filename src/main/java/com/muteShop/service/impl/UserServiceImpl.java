package com.muteShop.service.impl;

import com.muteShop.dao.UserDOMapper;
import com.muteShop.dao.UserPasswordDOMapper;
import com.muteShop.dataobject.UserDO;
import com.muteShop.dataobject.UserPasswordDO;
import com.muteShop.error.BusinessException;
import com.muteShop.error.EmBusinessError;
import com.muteShop.service.UserService;
import com.muteShop.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.service.impl
 * @ClassName: UserServiceImpl
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-11 下午4:40
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
   @Autowired
    private   UserDOMapper userDOMapper;

   @Autowired
   private UserPasswordDOMapper userPasswordDOMapper;


    @Override
    public UserModel getUserById(Integer id) {
        //调用userDOMapper 获取到对应的dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null){
            return null;
        }
        //通过用户ID找到对应用户加密密码
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO,userPasswordDO);
    }

    @Override
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //参数校验
        if (StringUtils.isAllEmpty(userModel.getName())
                || userModel.getGender() == null
                || userModel.getAge() == null
                ||StringUtils.isAllEmpty(userModel.getTelphone())){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //实现 model ---> dataobject 的转化方法
        UserDO userDO = convertFromModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已注册");
        }
        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
        return;
    }
    private UserPasswordDO convertPasswordFromModel(UserModel userModel) {
        if(userModel == null){
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }
    private UserDO convertFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }
    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if (userDO == null ){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if ( userPasswordDO != null) {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
    return userModel;
    }
}
