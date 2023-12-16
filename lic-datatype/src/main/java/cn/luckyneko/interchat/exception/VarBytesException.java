package cn.luckyneko.interchat.exception;

/**
 * @Author Lucky_He
 * @Description 字节数组异常
 * @Date 2023/12/9 20:22
 */

public class VarBytesException extends BaseException {

    public VarBytesException() {
        super();
    }

    public VarBytesException(String message) {
        super(message);
    }

    public VarBytesException(String message, Throwable cause) {
        super(message, cause);
    }

    public VarBytesException(Throwable cause) {
        super(cause);
    }

    protected VarBytesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
