package cn.luckyneko.interchat.exception;

/**
 * @Author Lucky_He
 * @Description 可变整型数读取错误时抛出该异常
 * @Date 2023/11/19 00:34
 */

public class VarIntTooBigException extends VarIntException{

    public VarIntTooBigException() {
        super();
    }

    public VarIntTooBigException(String message) {
        super(message);
    }

    public VarIntTooBigException(String message, Throwable cause) {
        super(message, cause);
    }

    public VarIntTooBigException(Throwable cause) {
        super(cause);
    }

    protected VarIntTooBigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
