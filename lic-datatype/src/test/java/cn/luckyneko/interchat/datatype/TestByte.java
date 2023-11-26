package cn.luckyneko.interchat.datatype;

import cn.luckyneko.interchat.exception.ByteException;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @Author Lucky_He
 * @Description 字节测试
 * @Date 2023/11/26 20:59
 */

public class TestByte {

    public static final Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new ByteException(e);
        }
    }

    @Test
    public void testConstructorInputStreamBytesToValue() {
        for (int i = 0; i < 100; i++) {
            byte v = ((byte) random.nextInt());
            Byte b = new Byte(new ByteArrayInputStream(new byte[] {v}));
            Assert.assertEquals(v, ((byte) b.getValue()));
        }
    }

    @Test
    public void testConstructorByteArrayInputStreamBytesToValue() {
        for (int i = 0; i < 100; i++) {
            byte v = ((byte) random.nextInt());
            Byte b = new Byte(new byte[] {v});
            Assert.assertEquals(v, ((byte) b.getValue()));
        }
    }

    @Test
    public void testConstructorValueToBytes() {
        for (int i = 0; i < 100; i++) {
            byte b = ((byte) random.nextInt());
            Byte b2 = new Byte(b);
            Assert.assertEquals(b, b2.getBytes()[0]);
        }
    }

}
