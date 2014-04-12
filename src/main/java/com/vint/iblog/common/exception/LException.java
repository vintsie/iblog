package com.vint.iblog.common.exception;

/**
 *
 *
 * Created by Vin on 14-4-12.
 */
public class LException extends Exception {

    public LException(String key) throws Exception{
        throw new Exception(key);
    }

}
