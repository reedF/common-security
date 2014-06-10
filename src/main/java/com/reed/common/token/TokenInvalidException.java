/**
 * TokenInvalidException.java
 * Copyright (c) 2013 by lashou.com
 */
package com.reed.common.token;

/**
 * token invalid exception
 * @author reed
 *
 */
public class TokenInvalidException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7861647682646392551L;

    /**
     * constructor
     * @param message Exception message
     */
    public TokenInvalidException(final String message) {
        super(message);
    }

}
