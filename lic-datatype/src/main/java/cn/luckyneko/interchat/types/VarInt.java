package cn.luckyneko.interchat.types;

import cn.luckyneko.interchat.types.utils.BitOperationNumbers;

import java.util.Arrays;

/**
 * Integer data type.
 *
 * @author Lucky_He
 */
public final class VarInt extends BaseType<Integer> {
    /**
     * VarInt constructor.
     *
     * @param v original value
     */
    public VarInt(final Integer v) {
        super(v);
    }

    @Override
    public byte[] getBytes() {
        int lowSeven = BitOperationNumbers.BYTE_LOW_SEVEN_BITS_SET;
        int highestBit = BitOperationNumbers.BYTE_HIGHEST_BIT_SET;
        int movingBits = BitOperationNumbers.VAR_MOVING_BITS;

        int temp = getValue();
        byte[] bytes = new byte[lowSeven];
        int i = 0;

        while (true) {
            if ((temp & ~lowSeven) == 0) {
                bytes[i] = ((byte) temp);
                return Arrays.copyOfRange(bytes, 0, i + 1);
            }

            bytes[i] = ((byte) ((temp & lowSeven) | highestBit));
            temp >>>= movingBits;
            i++;
        }
    }

    @Override
    public int getBytesLength() {
        return getBytes().length;
    }
}
