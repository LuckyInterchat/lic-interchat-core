package cn.luckyneko.interchat.exception;

/**
 * @Author Lucky_He
 * @Description 字节数组异常
 * @Date 2023/12/9 20:22
 */

public class VarByteException extends BaseException {

    public VarByteException() {
        super();
    }

    public VarByteException(String message) {
        super(message);
    }

    public VarByteException(String message, Throwable cause) {
        super(message, cause);
    }

    public VarByteException(Throwable cause) {
        super(cause);
    }

    protected VarByteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
