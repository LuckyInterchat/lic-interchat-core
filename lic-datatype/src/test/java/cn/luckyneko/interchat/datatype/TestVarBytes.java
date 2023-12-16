package cn.luckyneko.interchat.datatype;

import cn.luckyneko.interchat.datatype.util.ByteUtils;
import cn.luckyneko.interchat.exception.VarBytesException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Lucky_He
 * @Description VarBytes 测试
 * @Date 2023/12/9 21:25
 */

public class TestVarBytes {

    private static List<byte[]> bytesList;
    private static List<byte[]> valueList;
    private static List<Integer> lenList;

    @BeforeClass
    public static void beforeClass() {
        bytesList = new LinkedList<>();
        valueList = new LinkedList<>();
        lenList = new LinkedList<>();

        // no bytes
        bytesList.add(new byte[] {0x00});
        valueList.add(new byte[] {});
        lenList.add(1);

        byte[] bytes = {0x00, 0x01, 0x02, 0x03};

        // with bytes
        bytesList.add(ByteUtils.byteMergeAll(new byte[]{0x04}, bytes));
        valueList.add(bytes);
        lenList.add(5);

        bytes = new byte[] {
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        };

        // with bytes
        bytesList.add(ByteUtils.byteMergeAll(new byte[]{((byte) 0x80), 0x01}, bytes));
        valueList.add(bytes);
        lenList.add(130);
    }

    @Test(expected = VarBytesException.class)
    public void testEmptyInputStream() {
        ByteArrayInputStream is = new ByteArrayInputStream(new byte[] {});
        new VarBytes(is);
    }

    @Test(expected = VarBytesException.class)
    public void testEmptyBytesArray() {
        new VarBytes(new byte[] {}, VarBytes.Type.PACKED);
    }

    @Test
    public void testConstructorInputStreamBytesToValue() {
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes = bytesList.get(i);
            VarBytes varBytes = new VarBytes(new ByteArrayInputStream(bytes));
            byte[] correctResult = valueList.get(i);
            Assert.assertEquals(correctResult.length, varBytes.getValue().length);
            for (int j = 0; j < correctResult.length; j++) {
                Assert.assertEquals(correctResult[j], varBytes.getValue()[j]);
            }
        }
    }

    @Test
    public void testConstructorByteArrayInputStreamBytesToValue() {
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes = bytesList.get(i);
            VarBytes varBytes = new VarBytes(bytes, VarBytes.Type.PACKED);
            byte[] correctResult = valueList.get(i);
            Assert.assertEquals(correctResult.length, varBytes.getValue().length);
            for (int j = 0; j < correctResult.length; j++) {
                Assert.assertEquals(correctResult[j], varBytes.getValue()[j]);
            }
        }
    }

    @Test
    public void testConstructorValueToBytes() {
        for (int i = 0; i < valueList.size(); i++) {
            byte[] value = valueList.get(i);
            VarBytes varBytes = new VarBytes(value, VarBytes.Type.VALUE);
            byte[] bytes = varBytes.getBytes();
            for (int j = 0; j < bytes.length; j++) {
                Assert.assertEquals(bytes[j], bytesList.get(i)[j]);
            }
        }
    }

    @Test
    public void testBytesLengthValueToBytes() {
        for (int i = 0; i < valueList.size(); i++) {
            byte[] value = valueList.get(i);
            VarBytes varBytes = new VarBytes(value, VarBytes.Type.VALUE);
            int correctLen = lenList.get(i);
            int len = varBytes.getBytesLength();
            Assert.assertEquals(len, correctLen);
        }
    }

    @Test
    public void testBytesLengthBytesToValue() {
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes = bytesList.get(i);
            VarBytes varBytes = new VarBytes(bytes, VarBytes.Type.PACKED);
            int correctLen = lenList.get(i);
            Assert.assertEquals(varBytes.getBytesLength(), correctLen);
        }
    }

}
