/**
 * TokenValid.java
 * Copyright (c) 2013 by lashou.com
 */
package com.reed.common.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * token annotation
 * @author reed
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenValid {

    /** 保存token */
    boolean saveToken() default false;

    /** 删除token */
    boolean removeToken() default false;

}
