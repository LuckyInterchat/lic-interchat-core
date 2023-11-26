package cn.luckyneko.interchat.datatype;

import cn.luckyneko.interchat.datatype.util.ByteUtils;
import cn.luckyneko.interchat.exception.VarStringException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @Author Lucky_He
 * @Description VarString 测试类
 * @Date 2023/11/20 23:49
 */

public class TestVarString {

    private static List<byte[]> bytesList;
    private static List<String> stringList;
    private static List<Integer> lenList;
    private static final Charset charset = StandardCharsets.UTF_8;

    @BeforeClass
    public static void beforeClass() {
        bytesList = new LinkedList<>();
        stringList = new LinkedList<>();
        lenList = new LinkedList<>();

        // 3 char
        String nmb = "nmb";
        bytesList.add(ByteUtils.byteMergeAll(new VarInt(nmb.getBytes(charset).length).getBytes(), nmb.getBytes(charset)));
        stringList.add(nmb);
        lenList.add(ByteUtils.byteMergeAll(new VarInt(nmb.getBytes(charset).length).getBytes(), nmb.getBytes(charset)).length);

        // with chzn
        String chzn = "中文测试";
        bytesList.add(ByteUtils.byteMergeAll(new VarInt(chzn.getBytes(charset).length).getBytes(), chzn.getBytes(charset)));
        stringList.add(chzn);
        lenList.add(ByteUtils.byteMergeAll(new VarInt(chzn.getBytes(charset).length).getBytes(), chzn.getBytes(charset)).length);

        // empty
        String empty = "";
        bytesList.add(new byte[] {0x00});
        stringList.add(empty);
        lenList.add(1);

        // null
        String n = null;
        bytesList.add(new byte[] {0x00});
        stringList.add(null);
        lenList.add(1);
    }

    @Test(expected = VarStringException.class)
    public void testEmptyInputStream() {
        ByteArrayInputStream is = new ByteArrayInputStream(new byte[] {});
        new VarString(is);
    }

    @Test(expected = VarStringException.class)
    public void testEmptyBytesArray() {
        new VarString(new byte[] {});
    }

    @Test
    public void testConstructorInputStreamBytesToValue() {
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes = bytesList.get(i);
            VarString varString = new VarString(new ByteArrayInputStream(bytes));
            String correctResult = stringList.get(i);
            correctResult = Optional.ofNullable(correctResult).orElse("");
            Assert.assertEquals(varString.getValue(), correctResult);
        }
    }

    @Test
    public void testConstructorByteArrayInputStreamBytesToValue() {
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes = bytesList.get(i);
            VarString varString = new VarString(bytes);
            String correctResult = stringList.get(i);
            correctResult = Optional.ofNullable(correctResult).orElse("");
            Assert.assertEquals(varString.getValue(), correctResult);
        }
    }

    @Test
    public void testConstructorValueToBytes() {
        for (int i = 0; i < stringList.size(); i++) {
            String value = stringList.get(i);
            VarString varString = new VarString(value);
            byte[] bytes = varString.getBytes();
            for (int j = 0; j < bytes.length; j++) {
                Assert.assertEquals(bytes[j], bytesList.get(i)[j]);
            }
        }
    }

    @Test
    public void testBytesLengthValueToBytes() {
        for (int i = 0; i < stringList.size(); i++) {
            String value = stringList.get(i);
            VarString varString = new VarString(value);
            int correctLen = lenList.get(i);
            int len = varString.getBytesLength();
            Assert.assertEquals(len, correctLen);
        }
    }

    @Test
    public void testBytesLengthBytesToValue() {
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes = bytesList.get(i);
            VarString varString = new VarString(bytes);
            int correctLen = lenList.get(i);
            Assert.assertEquals(varString.getBytesLength(), correctLen);
        }
    }

}
