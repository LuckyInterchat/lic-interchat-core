package cn.luckyneko.interchat.datatype;

import cn.luckyneko.interchat.datatype.util.ByteUtils;
import cn.luckyneko.interchat.exception.VarIntException;
import cn.luckyneko.interchat.exception.VarStringLengthNotMatchException;
import cn.luckyneko.interchat.exception.VarStringException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @Author Lucky_He
 * @Description 字符串
 * @Date 2023/11/20 23:27
 */

public class VarString implements IDataType<String> {

    /**
     * 统一使用的字符编码
     */
    private static final Charset charset = StandardCharsets.UTF_8;

    /**
     * 字符串原文
     */
    private final String value;

    /**
     * VarString 构造方法
     * <p>
     * 直接从输入流构造实例
     * </p>
     * @param inputStream 输入流
     */
    public VarString(InputStream inputStream) {
        if (inputStream == null) {
            throw new VarStringException("inputStream is null");
        }

        byte[] bytes;
        try {
            VarInt len = new VarInt(inputStream);
            bytes = new byte[len.getValue()];
            int readLen = inputStream.read(bytes);
            if (readLen != len.getValue()) {
                throw new VarStringLengthNotMatchException("VarString length not match!");
            }
        } catch (VarIntException | VarStringLengthNotMatchException | IOException e) {
            throw new VarStringException(e);
        }

        this.value = new String(bytes, charset);
    }

    /**
     * VarString 构造方法
     * <p>
     * 通过字节数组构造实例
     * </p>
     * @param bytes 字节数组
     */
    public VarString(byte[] bytes) {
        this(new ByteArrayInputStream(bytes == null ? new byte[] {} : bytes));
    }

    @Override
    public byte[] getBytes() {
        byte[] bytes = this.value.getBytes(charset);
        byte[] lengthBytes = (new VarInt(bytes.length)).getBytes();
        return ByteUtils.byteMergeAll(lengthBytes, bytes);
    }

    @Override
    public int getBytesLength() {
        return Optional.ofNullable(getBytes()).orElse(new byte[] {}).length;
    }

    @Override
    public String getValue() {
        return Optional.ofNullable(this.value).orElse("");
    }

}
