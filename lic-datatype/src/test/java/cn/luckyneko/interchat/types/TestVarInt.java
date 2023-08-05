package cn.luckyneko.interchat.types;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @Author Lucky_He
 * @Description
 * @Date 2023/8/6 06:59
 */

public class TestVarInt {
    public VarInt getInstance(Integer integer) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<VarInt> varIntClass = VarInt.class;
        Constructor<VarInt> declaredConstructor = varIntClass.getDeclaredConstructor(Integer.class);
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance(integer);
    }

    @Test
    public void testPositive() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        VarInt value127 = getInstance(127);
        System.out.println("value127.getBytesLength() = " + value127.getBytesLength());
        System.out.println("value127.getBytes() = " + Arrays.toString(value127.getBytes()));

        VarInt value128 = getInstance(128);
        System.out.println("value128.getBytesLength() = " + value128.getBytesLength());
        System.out.println("value128.getBytes() = " + Arrays.toString(value128.getBytes()));

        VarInt value16383 = getInstance(16383);
        System.out.println("value16383.getBytesLength() = " + value16383.getBytesLength());
        System.out.println("value16383.getBytes() = " + Arrays.toString(value16383.getBytes()));

        VarInt value16384 = getInstance(16384);
        System.out.println("value16384.getBytesLength() = " + value16384.getBytesLength());
        System.out.println("value16384.getBytes() = " + Arrays.toString(value16384.getBytes()));
    }

    @Test
    public void testNegative() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        VarInt valueN1 = getInstance(-1);
        System.out.println("valueN1.getBytesLength() = " + valueN1.getBytesLength());
        System.out.println("valueN1.getBytes() = " + Arrays.toString(valueN1.getBytes()));

        VarInt valueN2147483648 = getInstance(-2147483648);
        System.out.println("valueN2147483648.getBytesLength() = " + valueN2147483648.getBytesLength());
        System.out.println("valueN2147483648.getBytes() = " + Arrays.toString(valueN2147483648.getBytes()));
    }
}
