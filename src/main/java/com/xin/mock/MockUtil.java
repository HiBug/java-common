package com.xin.mock;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Field;

/**
 * @author Three
 * @since 18/6/20下午2:37
 */
public class MockUtil {
    public MockUtil() {
    }

    public static void injectMock(Object clazzHandler, String fieldName, Object Handler) throws Exception {
        if (!AopUtils.isAopProxy(clazzHandler)) {
            ReflectionTestUtils.setField(clazzHandler, fieldName, Handler);
        } else {
            Object target = null;
            if (AopUtils.isJdkDynamicProxy(clazzHandler)) {
                target = getJdkDynamicProxyTargetObject(clazzHandler);
            } else {
                target = getCglibProxyTargetObject(clazzHandler);
                ReflectionTestUtils.setField(clazzHandler, fieldName, Handler);
            }

            ReflectionTestUtils.setField(target, fieldName, Handler);
        }

    }

    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field  advised                   = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        return target;
    }

    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field    advised  = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
        return target;
    }
}
