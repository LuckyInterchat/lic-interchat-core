package cn.luckyneko.interchat.datatype;

/**
 * @Author Lucky_He
 * @Description 数据类型基类
 * @Date 2023/11/19 00:24
 */

public interface IDataType<T> {

    /**
     * 返回字节数组
     *
     * @return 字节数组
     */
    byte[] getBytes();

    /**
     * 返回字节数组长度
     *
     * @return 字节数组长度
     */
    int getBytesLength();

    /**
     * 返回原文
     *
     * @return 原文
     */
    T getValue();

}
