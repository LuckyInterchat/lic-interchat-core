package cn.luckyneko.interchat.types;

/**
 * Base class of all data type classes.
 * <br>
 * All the data type classes must extend this base abstract class. The param
 * {@code  SourceType} is its original data type from Java.
 *
 * @param <SourceType>
 * @author Lucky_He
 */
public abstract class BaseType<SourceType> {
    /**
     * Original value.
     */
    private final SourceType value;

    /**
     * Base class constructor.
     * @param v original value
     */
    protected BaseType(final SourceType v) {
        this.value = v;
    }

    /**
     * Gets byte array of the data.
     *
     * @return byte array of the data
     */
    public abstract byte[] getBytes();

    /**
     * Gets length of byte array of the data.
     *
     * @return length of byte array of the data
     */
    public abstract int getBytesLength();

    /**
     * Gets original value.
     *
     * @return original value
     */
    public SourceType getValue() {
        return value;
    }
}
