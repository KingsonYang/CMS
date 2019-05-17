package com.cms.base.exception;

import com.cms.util.ResultCodeEnum;

/**
 * Created by hs on 2019.5.14.
 */
public class BizException extends RuntimeException{

    private String code;

    private String msg;

    public BizException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMsg());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public BizException setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BizException setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
