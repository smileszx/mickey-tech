package com.mickey.tech.common.core.exception.handler;

import com.mickey.tech.common.core.enums.ErrorCodeEnum;
import com.mickey.tech.common.core.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常捕获
 * @author suzhengxiao
 * @date 2020/1/9 17:49
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 接口参数校验
     * @param e
     * @return CommonResult
     * @author suzhengxiao
     * @date 2020/1/9 17:59
     */
    @ExceptionHandler(BindException.class)
    public CommonResult handleBindException(BindException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        if (!CollectionUtils.isEmpty(errors)) {
            errors.forEach((error -> errorMessage.append(error.getDefaultMessage()).append(";")));
        }
        return CommonResult.error(ErrorCodeEnum.ILLEGAL_PARAMETERS.getCode(), errorMessage.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        if (errors != null) {
            errors.forEach((error -> errorMessage.append(error.getDefaultMessage()).append(";")));
        }
        return CommonResult.error(ErrorCodeEnum.ILLEGAL_PARAMETERS.getCode(), errorMessage.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResult handleIllegalArgumentException(IllegalArgumentException e) {
        return CommonResult.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error("全局捕获controller异常:", e);
        return CommonResult.error();
    }
}
