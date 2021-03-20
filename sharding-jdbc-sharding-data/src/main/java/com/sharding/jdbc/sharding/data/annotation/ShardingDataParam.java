package com.sharding.jdbc.sharding.data.annotation;

import java.lang.annotation.*;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ShardingDataParam {
    String value();

    boolean required() default true;

    String defaultValue() default "orderId";
}
