package cn.luckyneko.interchat.datatype;

import cn.luckyneko.interchat.datatype.util.ByteUtils;
import cn.luckyneko.interchat.exception.VarBytesException;
import cn.luckyneko.interchat.exception.VarBytesLengthNotMatchException;
import cn.luckyneko.interchat.exception.VarIntException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;

/**
 * @Author Lucky_He
 * @Description 字节数组
 * @Date 2023/12/9 20:20
 */

public class VarBytes implements IDataType<byte[]>{

    /**
     * 字节数组原文
     */
    private final byte[] value;

    /**
     * VarBytes 构造方法
     * <p>
     * 直接从输入流构造实例
     * </p>
     * @param inputStream 输入流
     */
    public VarBytes(InputStream inputStream) {
        if (inputStream == null) {
            throw new VarBytesException("inputStream is null");
        }

        byte[] bytes;
        try {
            VarInt len = new VarInt(inputStream);
            if (len.getValue() == 0) {
                this.value = new byte[] {};
                return;
            }
            bytes = new byte[len.getValue()];
            int readLen = inputStream.read(bytes);
            if (readLen != len.getValue()) {
                throw new VarBytesLengthNotMatchException("VarBytes length not match");
            }
        } catch (VarIntException | VarBytesLengthNotMatchException | IOException e) {
            throw new VarBytesException(e);
        }

        this.value = bytes;
    }

    /**
     * VarBytes 构造方法
     * <p>
     * 通过字节数组构造实例
     * </p>
     * @param bytes 字节数组
     */
    public VarBytes(byte[] bytes, Type type) {
        if (type == Type.PACKED) {
            VarInt len;
            try {
                len = new VarInt(bytes);
                if (len.getValue() == 0) {
                    this.value = new byte[] {};
                    return;
                }
                // 除去 VarInt 后的剩余长度比 VarInt 值小，则说明数组不完整
                if (bytes.length - len.getBytesLength() < len.getValue()) {
                    throw new VarBytesLengthNotMatchException("VarBytes length no match");
                }
            } catch (VarIntException | VarBytesLengthNotMatchException e) {
                throw new VarBytesException(e);
            }

            this.value = Arrays.copyOfRange(bytes, len.getBytesLength(), len.getBytesLength() + len.getValue());
        } else if (type == Type.VALUE) {
            this.value = bytes;
        } else {
            throw new VarBytesException("Type not exist");
        }
    }

    /**
     * 枚举类，为了区分来源
     *
     * PACKED: VarInt(字节数组长度) + 字节数组
     * VALUE: 纯字节数组
     */
    public enum Type {
        PACKED,
        VALUE
    }

    @Override
    public byte[] getBytes() {
        byte[] bytes = getValue();
        byte[] lengthBytes = (new VarInt(bytes.length)).getBytes();
        return ByteUtils.byteMergeAll(lengthBytes, bytes);
    }

    @Override
    public int getBytesLength() {
        return Optional.ofNullable(getBytes()).orElse(new byte[] {}).length;
    }

    @Override
    public byte[] getValue() {
        return Optional.ofNullable(this.value).orElse(new byte[] {});
    }

}
