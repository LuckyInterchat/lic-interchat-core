package cn.luckyneko.interchat.datatype;

import cn.luckyneko.interchat.exception.ByteException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @Author Lucky_He
 * @Description
 * @Date 2023/11/26 20:19
 */

public class Byte implements IDataType<java.lang.Byte> {

    /**
     * 字节原文
     */
    private final java.lang.Byte value;

    /**
     * Byte 构造方法
     * <p>
     * 直接从输入流构造实例
     * </p>
     *
     * @param inputStream 输入流
     */
    public Byte(InputStream inputStream) {
        if (inputStream == null) {
            throw new ByteException("inputStream is null");
        }

        try {
            if (inputStream.available() <= 0) {
                throw new ByteException("inputStream has no byte");
            }
            this.value = ((byte) inputStream.read());
        } catch (IOException e) {
            throw new ByteException(e);
        }
    }

    /**
     * Byte 构造方法
     * <p>
     * 通过字节数组构造实例
     * </p>
     *
     * @param bytes 字节数组
     */
    public Byte(byte[] bytes) {
        this(new ByteArrayInputStream(bytes == null ? new byte[] {} : bytes));
    }

    /**
     * Byte 构造方法
     * <p>
     * 直接通过原文构造
     * </p>
     * @param value 字节原文
     */
    public Byte(java.lang.Byte value) {
        this.value = value;
    }

    @Override
    public byte[] getBytes() {
        return new byte[] {this.value};
    }

    @Override
    public int getBytesLength() {
        return Optional.ofNullable(getBytes()).orElse(new byte[] {}).length;
    }

    @Override
    public java.lang.Byte getValue() {
        return Optional.ofNullable(this.value).orElse(((byte) 0));
    }

}
