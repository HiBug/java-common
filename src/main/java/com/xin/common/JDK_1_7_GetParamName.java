package com.xin.common;

import com.xin.util.Printer;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

/**
 * @author Three
 * @since 18/6/26下午3:06
 */
public class JDK_1_7_GetParamName {

    /**
     * <pre>
     * jdk1.7之前如何获取method的参数名
     *
     *
     * </pre>
     *
     * @author Three
     * @since 18/6/26 下午3:10
     */
    public static void main(String[] args) throws Exception {
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        Printer.jsonPrint(discoverer.getParameterNames(JDK_1_7_GetParamName.class.getMethod("main", String[].class)));
    }
}
