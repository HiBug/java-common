package com.xin;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println(new BigDecimal("3E-9").toPlainString());
    }
}
