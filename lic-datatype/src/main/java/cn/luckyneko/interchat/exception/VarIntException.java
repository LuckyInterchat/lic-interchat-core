package cn.luckyneko.interchat.exception;

/**
 * @Author Lucky_He
 * @Description 可变整型异常
 * @Date 2023/11/19 00:39
 */

public class VarIntException extends BaseException {

    public VarIntException() {
        super();
    }

    public VarIntException(String message) {
        super(message);
    }

    public VarIntException(String message, Throwable cause) {
        super(message, cause);
    }

    public VarIntException(Throwable cause) {
        super(cause);
    }

    protected VarIntException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
