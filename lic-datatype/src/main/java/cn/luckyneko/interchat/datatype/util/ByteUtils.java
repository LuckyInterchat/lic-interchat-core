package cn.luckyneko.interchat.datatype.util;

/**
 * @Author Lucky_He
 * @Description 字节工具类
 * @Date 2023/11/20 23:41
 */

public class ByteUtils {

    /**
     * 合成多个数组为一个
     *
     * @param values 数组的数组
     * @return 合成后的数组
     */
    public static byte[] byteMergeAll(byte[] ...values) {
        int length = 0;
        for (byte[] value : values) {
            length += value.length;
        }

        byte[] allBytes = new byte[length];
        int count = 0;
        for (byte[] value : values) {
            System.arraycopy(value, 0, allBytes, count, value.length);
            count += value.length;
        }

        return allBytes;
    }

}
