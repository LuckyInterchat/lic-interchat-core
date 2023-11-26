package cn.luckyneko.interchat.exception;

/**
 * @Author Lucky_He
 * @Description 字节异常
 * @Date 2023/11/26 20:21
 */

public class ByteException extends BaseException {

    public ByteException() {
        super();
    }

    public ByteException(String message) {
        super(message);
    }

    public ByteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ByteException(Throwable cause) {
        super(cause);
    }

    protected ByteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
