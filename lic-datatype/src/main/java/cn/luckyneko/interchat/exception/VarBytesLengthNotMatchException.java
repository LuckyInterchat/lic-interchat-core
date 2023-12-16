package cn.luckyneko.interchat.exception;

/**
 * @Author Lucky_He
 * @Description 字节数组给定长度与实际获取长度不一致异常
 * @Date 2023/12/9 20:24
 */

public class VarBytesLengthNotMatchException extends VarBytesException {

    public VarBytesLengthNotMatchException() {
        super();
    }

    public VarBytesLengthNotMatchException(String message) {
        super(message);
    }

    public VarBytesLengthNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public VarBytesLengthNotMatchException(Throwable cause) {
        super(cause);
    }

    protected VarBytesLengthNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
