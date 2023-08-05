package cn.luckyneko.interchat.types;

import cn.luckyneko.interchat.types.utils.BitOperationNumbers;
import exceptions.BaseException;
import exceptions.VarIntTooBigException;

import java.io.IOException;
import java.io.InputStream;
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

    /**
     * Gets VarInt instance from input stream.
     *
     * @param inputStream an input stream instance
     */
    public VarInt(
            final InputStream inputStream
    ) throws IOException, BaseException {
        super(inputStream);

        int lowSeven = BitOperationNumbers.BYTE_LOW_SEVEN_BITS_SET;
        int highestBit = BitOperationNumbers.BYTE_HIGHEST_BIT_SET;
        int movingBits = BitOperationNumbers.VAR_MOVING_BITS;
        int maxLen = BitOperationNumbers.MAX_VAR_INT_BYTES_LENGTH;

        int value = 0;
        int length = 0;
        byte currentByte;

        do {
            currentByte = ((byte) inputStream.read());
            value |= (currentByte & lowSeven) << (length * movingBits);
            length++;

            if (length > maxLen) {
                throw new VarIntTooBigException();
            }
        } while ((currentByte & highestBit) == highestBit);

        this.value = value;
    }

    @Override
    public byte[] getBytes() {
        int lowSeven = BitOperationNumbers.BYTE_LOW_SEVEN_BITS_SET;
        int highestBit = BitOperationNumbers.BYTE_HIGHEST_BIT_SET;
        int movingBits = BitOperationNumbers.VAR_MOVING_BITS;

        int temp = this.value;
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
