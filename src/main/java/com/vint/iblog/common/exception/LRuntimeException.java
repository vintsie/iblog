package com.vint.iblog.common.exception;


/**
 *
 * Created by Vin on 14-4-12.
 */
public class LRuntimeException extends RuntimeException {

    public LRuntimeException(String key) throws RuntimeException{
        throw new RuntimeException(key);
    }
}
