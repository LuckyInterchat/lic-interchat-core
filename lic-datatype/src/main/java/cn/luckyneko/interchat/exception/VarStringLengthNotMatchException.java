package cn.luckyneko.interchat.exception;

/**
 * @Author Lucky_He
 * @Description 字符串给定长度与实际获取长度不一致异常
 * @Date 2023/11/20 23:35
 */

public class VarStringLengthNotMatchException extends VarStringException {

    public VarStringLengthNotMatchException() {
        super();
    }

    public VarStringLengthNotMatchException(String message) {
        super(message);
    }

    public VarStringLengthNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public VarStringLengthNotMatchException(Throwable cause) {
        super(cause);
    }

    protected VarStringLengthNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
