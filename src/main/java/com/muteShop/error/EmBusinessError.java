package com.muteShop.error;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.error
 * @ClassName: EmBusinessError
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-11 下午7:17
 * @Version: 1.0
 */
public enum EmBusinessError implements CommonError {
    //通用错误类型100001
    PARAMETER_VALIDATION_ERROR(100001,"参数不合法"),
    UNKNOWN_ERROR(100002,"未知错误"),
    //20000 开头的为用户相关错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    ;

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;
    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }
}
