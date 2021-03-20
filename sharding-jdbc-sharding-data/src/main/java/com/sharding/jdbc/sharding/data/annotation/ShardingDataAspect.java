package com.sharding.jdbc.sharding.data.annotation;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/***
 * 根据参数名：orderIds，orderId，saleOrderItems``
 * 获取分片键值
 */
@Slf4j
@Aspect
@Component
public class ShardingDataAspect {
    // serviceimpl, mapper为切点
    @Pointcut("@annotation(com.sharding.jdbc.sharding.data.annotation.ShardingData)")
    public void annotationPoinCut() {
    }

    @Before("annotationPoinCut()")
    public void before(JoinPoint joinPoint) {
        log.info("================before===================");
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法，此处可将signature强转为MethodSignature
        Method method = methodSignature.getMethod();
        log.info("ShardingDataAspect before args:{}", JSON.toJSONString(args));
        log.info("execution method name:{}, {}", method.getDeclaringClass().getName(), method.getName());
        //参数注解，1维是参数，2维是注解
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            Object parameter = args[i];
            Annotation[] listAnnotation = annotations[i];
            //参数为空，直接下一个参数
            if (parameter == null || listAnnotation.length == 0) {
                continue;
            }
            for (Annotation annotation : listAnnotation) {
                //这里判断当前注解是否为ShardingDataParam.class
                if (annotation.annotationType().equals(com.sharding.jdbc.sharding.data.annotation.ShardingDataParam.class)) {
                    ShardingDataParam shardingDataParam = (com.sharding.jdbc.sharding.data.annotation.ShardingDataParam) annotation;
                    // orderId, orderIds ...
                    String value = shardingDataParam.value();
                    log.info("annotation value:{}", value);
                }
            }
        }
    }

    @After("annotationPoinCut()")
    public void after() {
        log.info("================after===================");
    }

    @AfterThrowing("annotationPoinCut()")
    public void afterThrowing() {
        log.info("================afterThrowing===================");
    }
}
