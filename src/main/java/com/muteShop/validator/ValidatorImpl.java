package com.muteShop.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @ProjectName: shop
 * @Package: com.muteShop.validator
 * @ClassName: ValidatorImpl
 * @Author: mute_luo
 * @Description: ${description}
 * @Date: 19-3-13 下午9:34
 * @Version: 1.0
 */
@Component
public class ValidatorImpl implements InitializingBean{

    private Validator validator;
    //实现校验方法并返回校验结果
    public ValidationResult validate(Object bean){
        final ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> validateSet = validator.validate(bean);
        if (validateSet.size()>0){
            //有错误
            result.setHasErrors(true);
            //lambd 表达式遍历
            validateSet.forEach(consttraintViolation->{
                String errMsg = consttraintViolation.getMessage();
                String propertyNmae = consttraintViolation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyNmae,errMsg);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator 通过工厂实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

    }
}
