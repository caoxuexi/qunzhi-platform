package com.xidian.qunzhi.validator;

import com.xidian.qunzhi.validator.related.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented//使得注释能够被加入到文档里面
@Retention(RetentionPolicy.RUNTIME)//注解被保留到什么阶段
@Target({ElementType.TYPE})//指定这个注解可以用在哪些上面
@Constraint(validatedBy = PasswordValidator.class)//这个是可以传入数组，指定多个校验类的
public @interface PasswordEqual {
    int min() default 5;
    int max() default 16;
    String message() default "两次密码输入不相同";

    //规范里要求要有这两个东西
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    //关联类
}