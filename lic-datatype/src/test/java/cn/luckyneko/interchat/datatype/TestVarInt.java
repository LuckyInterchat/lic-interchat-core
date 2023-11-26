package cn.luckyneko.interchat.datatype;

import cn.luckyneko.interchat.exception.VarIntException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Lucky_He
 * @Description VarInt 测试类
 * @Date 2023/11/19 00:57
 */

public class TestVarInt {

    private static List<byte[]> bytesList;
    private static List<Integer> numList;
    private static List<Integer> lenList;

    @BeforeClass
    public static void beforeClass() {
        bytesList = new LinkedList<>();
        numList = new LinkedList<>();
        lenList = new LinkedList<>();

        // test 1 byte
        bytesList.add(new byte[]{0x01});
        numList.add(1);
        lenList.add(1);
        // test 1 byte with extra bytes
        bytesList.add(new byte[]{0x01, ((byte) 0xFF)});
        numList.add(1);
        lenList.add(1);
        // test 2 bytes
        bytesList.add(new byte[]{((byte) 0x80), 0x01});
        numList.add(128);
        lenList.add(2);
        bytesList.add(new byte[]{((byte) 0xFF), 0x7F});
        numList.add(16383);
        lenList.add(2);
        // test2 bytes with extra bytes
        bytesList.add(new byte[]{((byte) 0xFF), 0x7F, 0x10});
        numList.add(16383);
        lenList.add(2);
        // test 3 bytes
        bytesList.add(new byte[]{((byte) 0x80), ((byte) 0x80), 0x01});
        numList.add(16384);
        lenList.add(3);
        bytesList.add(new byte[]{((byte) 0xFF), ((byte) 0xFF), 0x7F});
        numList.add(2097151);
        lenList.add(3);
        // test 4 bytes
        bytesList.add(new byte[]{((byte) 0x80), ((byte) 0x80), ((byte) 0x80), 0x01});
        numList.add(2097152);
        lenList.add(4);
        bytesList.add(new byte[]{((byte) 0xFF), ((byte) 0xFF), ((byte) 0xFF), 0x7F});
        numList.add(268435455);
        lenList.add(4);
        // test max value
        bytesList.add(new byte[]{((byte) 0xFF), ((byte) 0xFF), ((byte) 0xFF), ((byte) 0xFF), 0x07});
        numList.add(2147483647);
        lenList.add(5);
    }

    @Test(expected = NullPointerException.class)
    public void testByteArrayInputStreamByUsingArgNull() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(null);
        System.out.println(byteArrayInputStream.read());
    }

    @Test(expected = VarIntException.class)
    public void testGreaterThan5Bytes() {
        new VarInt(new ByteArrayInputStream(new byte[] {((byte) 0x80), ((byte) 0x80), ((byte) 0x80), ((byte) 0x80), ((byte) 0x80)}));
    }

    @Test
    public void testReadZeroByteArrayHasNext() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[]{});
        Assert.assertEquals(byteArrayInputStream.available(), 0);
    }

    @Test
    public void testConstructorInputStreamBytesToValue() {
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes = bytesList.get(i);
            VarInt varInt = new VarInt(new ByteArrayInputStream(bytes));
            int correctResult = numList.get(i);
            Assert.assertEquals(((int) varInt.getValue()), correctResult);
        }
    }

    @Test
    public void testConstructorByteArrayInputStreamBytesToValue() {
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes = bytesList.get(i);
            VarInt varInt = new VarInt(bytes);
            int correctResult = numList.get(i);
            Assert.assertEquals(((int) varInt.getValue()), correctResult);
        }
    }

    @Test
    public void testConstructorValueToBytes() {
        for (int i = 0; i < numList.size(); i++) {
            int value = numList.get(i);
            VarInt varInt = new VarInt(value);
            byte[] bytes = varInt.getBytes();
            for (int j = 0; j < bytes.length; j++) {
                Assert.assertEquals(bytes[j], bytesList.get(i)[j]);
            }
        }
    }

    @Test
    public void testBytesLengthValueToBytes() {
        for (int i = 0; i < numList.size(); i++) {
            int value = numList.get(i);
            VarInt varInt = new VarInt(value);
            int correctLen = lenList.get(i);
            int len = varInt.getBytesLength();
            Assert.assertEquals(len, correctLen);
        }
    }

    @Test
    public void testBytesLengthBytesToValue() {
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes = bytesList.get(i);
            VarInt varInt = new VarInt(bytes);
            int correctLen = lenList.get(i);
            Assert.assertEquals(varInt.getBytesLength(), correctLen);
        }
    }

}
