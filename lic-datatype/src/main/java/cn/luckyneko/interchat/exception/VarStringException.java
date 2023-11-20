package cn.luckyneko.interchat.exception;

/**
 * @Author Lucky_He
 * @Description 字符串异常
 * @Date 2023/11/20 23:28
 */

public class VarStringException extends BaseException {

    public VarStringException() {
        super();
    }

    public VarStringException(String message) {
        super(message);
    }

    public VarStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public VarStringException(Throwable cause) {
        super(cause);
    }

    protected VarStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
