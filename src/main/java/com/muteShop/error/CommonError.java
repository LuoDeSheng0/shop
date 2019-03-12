package com.muteShop.error;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.error
 * @ClassName: CommonError
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-11 下午7:15
 * @Version: 1.0
 */
public interface CommonError {
    public int getErrorCode();
    public String getErrMsg();
    public CommonError setErrorMsg(String errMsg);
}
