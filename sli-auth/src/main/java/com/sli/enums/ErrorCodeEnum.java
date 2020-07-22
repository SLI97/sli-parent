package com.sli.enums;

public enum ErrorCodeEnum {
    SY1000100(1000100, "参数异常"),
    /**
     * SY 000401 error code enum.
     */
    SY1000401(1000401, "无访问权限"),
    /**
     * SY 000500 error code enum.
     */
    SY1000500(1000500, "未知异常"),
    /**
     * SY 000403 error code enum.
     */
    SY1000403(1000403, "无权访问");

    private int code;

    private String msg;

    public String msg() {
        return msg;
    }

    public int code() {
        return code;
    }

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCodeEnum getEnum(int code) {
        for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
            if (ele.code() == code) {
                return ele;
            }
        }
        return null;
    }
}
