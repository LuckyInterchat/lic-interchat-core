package cn.luckyneko.interchat.datatype;

import cn.luckyneko.interchat.exception.VarIntException;
import cn.luckyneko.interchat.exception.VarIntTooBigException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;

/**
 * @Author Lucky_He
 * @Description 可变整型数
 * @Date 2023/11/19 00:26
 */

public class VarInt implements IDataType<Integer> {

    /**
     * 整型数原文
     */
    private final Integer value;

    /**
     * VarInt 构造方法
     * <p>
     * 直接从输入流构造实例
     * </p>
     *
     * @param inputStream 输入流
     */
    public VarInt(InputStream inputStream) {
        if (inputStream == null) {
            throw new VarIntException("inputStream is null");
        }

        int value = 0;
        int length = 0;
        byte currentByte;

        try {
            if (inputStream.available() <= 0) {
                throw new VarIntException("inputStream has no byte");
            }

            do {
                currentByte = ((byte) inputStream.read());
                value |= (currentByte & 0x7F) << (length * 7);
                length++;

                if (length > 5) {
                    throw new VarIntTooBigException("VarInt too big");
                }
            } while ((currentByte & 0x80) == 0x80);
        } catch (VarIntTooBigException | IOException e) {
            throw new VarIntException(e);
        }

        this.value = value;
    }

    /**
     * VarInt 构造方法
     * <p>
     * 通过字节数组构造实例，最多读取字节数组前 5 个字节
     * </p>
     *
     * @param bytes 字节数组
     */
    public VarInt(byte[] bytes) {
        this(new ByteArrayInputStream(bytes == null ? new byte[] {} : bytes));
    }

    public VarInt(int value) {
        this.value = value;
    }

    @Override
    public byte[] getBytes() {
        int value = this.getValue();
        byte[] bytes = new byte[5];
        int i = 0;

        while (true) {
            if ((value & ~0x7F) == 0) {
                bytes[i] = ((byte) value);
                return Arrays.copyOfRange(bytes, 0, i + 1);
            }

            bytes[i] = ((byte) ((value & 0x7F) | 0x80));
            value >>>= 7;
            i++;
        }
    }

    @Override
    public int getBytesLength() {
        if (getValue() < 0) {
            return 5;
        }
        return Optional.ofNullable(getBytes()).orElse(new byte[] {}).length;
    }

    @Override
    public Integer getValue() {
        return Optional.ofNullable(this.value).orElse(0);
    }

}
