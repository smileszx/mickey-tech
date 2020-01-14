package com.mickey.tech.common.core.util;

import com.mickey.tech.common.core.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 响应信息主体
 * @param <T>
 * @author suzhengxiao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public static <T> CommonResult<T> success() {
        return restResult(null, ErrorCodeEnum.SUCCESS.getCode(), null);
    }

    public static <T> CommonResult<T> success(T data) {
        return restResult(data, ErrorCodeEnum.SUCCESS.getCode(), null);
    }

    public static <T> CommonResult<T> success(T data, String msg) {
        return restResult(data, ErrorCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> CommonResult<T> error(ErrorCodeEnum enumResult) {
        return restResult(null, enumResult.getCode(), enumResult.getMsg());
    }

    public static <T> CommonResult<T> error(Integer code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> CommonResult<T> error(String msg) {
        return restResult(null, ErrorCodeEnum.SERVER_ERROR.getCode(), msg);
    }

	public static <T> CommonResult<T>  error(Integer code, String msg, T data) {
		return restResult(data, code, msg);
	}

    public static <T> CommonResult<T> error() {
        return restResult(null, ErrorCodeEnum.SERVER_ERROR.getCode(), ErrorCodeEnum.SERVER_ERROR.getMsg());
    }

    private static <T> CommonResult<T> restResult(T data, int code, String msg) {
        CommonResult<T> apiResult = new CommonResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}

