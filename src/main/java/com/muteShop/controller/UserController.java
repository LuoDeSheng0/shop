package com.muteShop.controller;

import com.muteShop.controller.viewobject.UserVO;
import com.muteShop.error.BusinessException;
import com.muteShop.error.EmBusinessError;
import com.muteShop.response.CommonReturnType;
import com.muteShop.service.UserService;
import com.muteShop.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * @ProjectName: shop
 * @Package: com.muteShop.controller
 * @ClassName: UserController
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-11 下午4:36
 * @Version: 1.0
 */
@Controller("user")
@RequestMapping("/user")
//跨域
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户注册
    @RequestMapping(value = "/register" ,method = {RequestMethod.POST} ,consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name ="telphone")String telphone,
                                     @RequestParam(name ="otpCode")String otpCode,
                                     @RequestParam(name ="name")String name,
                                     @RequestParam(name ="gender")Integer gender,
                                     @RequestParam(name ="age")Integer age,
                                     @RequestParam(name ="password")String password ) throws BusinessException, NoSuchAlgorithmException {
        //验证手机号和对应的otpCode是否相符合
        String inSessinOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if (!com.alibaba.druid.util.StringUtils.equals(otpCode,inSessinOtpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不正确");
        }
        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("/byphone");
        userModel.setEncrptPassword(this.EncodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }
    public String EncodeByMd5(String str) throws NoSuchAlgorithmException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newStr = base64Encoder.encode(md5.digest(str.getBytes()));
        return newStr;
    }

    //用户获取OTP短信接口
    @RequestMapping(value = "/getotp" ,method = {RequestMethod.POST} ,consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name ="telphone")String telphone){
    //按照规则生成验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

        // 将otp验证码同对应的手机号关联 redis 接入 暂时使用httpsession
        httpServletRequest.getSession().setAttribute(telphone,otpCode);;

     //将otp验证码通过短信通道发送给用户 ,省略
        System.out.println("telphone:"+telphone+"&验证码是：【"+otpCode+"】");
        return CommonReturnType.create(null);
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调用SERVICE服务获取对应ID的用户对象信息并返回给前段
        UserModel userModel = userService.getUserById(id);
        //若获取的用户信息不存在
        if(userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //将核心领域用户对象转化为可供UI
        UserVO userVO = convertFromModel(userModel);
        //返回通用对象
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }


}
