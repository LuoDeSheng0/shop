package com.muteShop.error;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.error
 * @ClassName: BusinessException
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-11 下午7:25
 * @Version: 1.0
 */
//包装器业务异常类实现
public class BusinessException extends Exception implements CommonError{
    private CommonError commonError;

    //直接接收EmBusinessException 的传参 用于构造业务异常
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError ;
    }
    //接收自定义errMsg 的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError ;
        this.commonError.setErrorMsg(errMsg);
    }


    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {
        this.setErrorMsg(errMsg);
        return this;
    }
}