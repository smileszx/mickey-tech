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

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 全局异常捕获
 * @author suzhengxiao
 * @date 2020/1/9 17:49
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 校验异常
     */
    @ExceptionHandler(BindException.class)
    public CommonResult handleBindException(BindException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        if (!CollectionUtils.isEmpty(errors)) {
            errors.forEach((error -> errorMessage.append(error.getDefaultMessage()).append(";")));
        }
        log.warn(errorMessage.toString(), e);
        return CommonResult.error(ErrorCodeEnum.ILLEGAL_PARAMETERS.getCode(), errorMessage.toString());
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        log.warn(msgList.toString(), e);
        return CommonResult.error(ErrorCodeEnum.ILLEGAL_PARAMETERS.getCode(), String.join(",", msgList));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        if (errors != null) {
            errors.forEach((error -> errorMessage.append(error.getDefaultMessage()).append(";")));
        }
        log.warn(errorMessage.toString(), e);
        return CommonResult.error(ErrorCodeEnum.ILLEGAL_PARAMETERS.getCode(), errorMessage.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResult handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn(e.getMessage(), e);
        return CommonResult.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error("全局捕获controller异常:", e);
        return CommonResult.error(e.getMessage());
    }

}
