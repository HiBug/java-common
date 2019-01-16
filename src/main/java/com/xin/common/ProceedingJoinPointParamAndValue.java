package com.xin.common;

import com.google.common.collect.Maps;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @author three
 * @since 2018/11/1 14:56
 * <p>
 *
 * </p>
 */
public class ProceedingJoinPointParamAndValue {
    //获取切面方法入参的名字和值
    private Map<String, Object> getFieldsNameAndValue(ProceedingJoinPoint pjp) throws Exception {
        String              classType  = pjp.getTarget().getClass().getName();
        Class<?>            clazz      = Class.forName(classType);
        String              clazzName  = clazz.getName();
        String              methodName = pjp.getSignature().getName(); //获取方法名称
        Object[]            args       = pjp.getArgs();//参数
        Map<String, Object> map        = Maps.newHashMap();

        ClassPool      pool      = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(this.getClass());
        pool.insertClassPath(classPath);

        CtClass                cc            = pool.get(clazzName);
        CtMethod               cm            = cc.getDeclaredMethod(methodName);
        MethodInfo             methodInfo    = cm.getMethodInfo();
        CodeAttribute          codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr          = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            throw new RuntimeException();
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            Object temp = args[i];
            map.put(attr.variableName(i + pos), temp);
            if (!isBaseDataType(temp)) {
                //不是基本类型
//                map.putAll(DataFormatUtils.objectToMap(temp));
            }
        }
        return map;
    }


    //判断是不是基本类型
    private static boolean isBaseDataType(Object o) throws Exception {
        Class clazz = o.getClass();
        return clazz.equals(String.class) ||
                clazz.equals(Integer.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Float.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(Boolean.class) ||
                clazz.isPrimitive();
    }
}
