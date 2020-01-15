package com.mickey.tech.common.core.exception;

/**
 * @author suzhengxiao
 * @date 2020/1/15 10:45
 **/
public class CustomException extends Exception{

    public CustomException(String message) {
        super("自定义异常");
    }
}
