package com.xin.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author three
 * @since 2019/3/21 22:18
 * <p>
 *
 * </p>
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {
    private String name;
    private String mobile;
    private String logo;
}
