package com.muteShop.response;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.response
 * @ClassName: CommonReturnType
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-11 下午5:45
 * @Version: 1.0
 */
public class CommonReturnType {
    private String status;//表明对应请求的返回处理结果 “success”或 “fail”

    //若status =success ，data 内返回前段需要的数据
    //若status = fail ，data 内使用通用的错误码格式
    private Object data;

    //定义通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result,String status){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(result);
        return commonReturnType;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
