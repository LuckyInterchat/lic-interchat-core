package cn.luckyneko.interchat.types.utils;

/**
 * Some numbers need to be used in bit operation of some data types.
 *
 * @author Lucky_He
 */
public final class BitOperationNumbers {
    private BitOperationNumbers() {

    }

    /**
     * The lowest 8 bits: 1000 0000.
     */
    public static final int BYTE_HIGHEST_BIT_SET = 0x80;

    /**
     * The lowest 8 bits: 0111 1111.
     */
    public static final int BYTE_LOW_SEVEN_BITS_SET = 0x7F;

    /**
     * Max bytes length of var int data type.
     */
    public static final int MAX_VAR_INT_BYTES_LENGTH = 5;

    /**
     * Var number types moving bit counts.
     */
    public static final int VAR_MOVING_BITS = 7;
}
