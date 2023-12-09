package cn.luckyneko.interchat.exception;

/**
 * @Author Lucky_He
 * @Description 字节数组给定长度与实际获取长度不一致异常
 * @Date 2023/12/9 20:24
 */

public class VarByteLengthNotMatchException extends VarByteException {

    public VarByteLengthNotMatchException() {
        super();
    }

    public VarByteLengthNotMatchException(String message) {
        super(message);
    }

    public VarByteLengthNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public VarByteLengthNotMatchException(Throwable cause) {
        super(cause);
    }

    protected VarByteLengthNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
