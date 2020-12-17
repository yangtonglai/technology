package com.shop.manage.system.aspect;

import com.shop.manage.system.annotation.DataSource;
import com.shop.manage.system.config.DynamicDataSource;
import com.shop.manage.system.contant.DataSourceConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源注解处理逻辑
 * @author Mr.joey
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {
    @Pointcut("@annotation( com.shop.manage.system.annotation.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds == null) {
            DynamicDataSource.setDataSource(DataSourceConstants.BASE);
        } else {
            DynamicDataSource.setDataSource(ds.value());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
