package com.sharding.jdbc.sharding.data.annotation;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ShardingData {
    String name() default "";
}
