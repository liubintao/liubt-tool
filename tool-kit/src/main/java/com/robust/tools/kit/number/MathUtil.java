package com.robust.tools.kit.number;

/**
 * @Description: 数学相关工具类
 * @Author: robust
 * @CreateDate: 2019/7/26 15:59
 * @Version: 1.0
 */
public class MathUtil {

    /**
     * copy from netty {@see io.netty.util.internal.MathUtil#safeFindNextPositivePowerOfTwo(int)}
     * <p>
     * Fast method of finding the next power of 2 greater than or equal to the supplied value.
     * <p>This method will do runtime bounds checking and call {@link #findNextPositivePowerOfTwo(int)} if within a
     * valid range.
     *
     * @param value from which to search for next power of 2
     * @return The next power of 2 or the value itself if it is a power of 2.
     * <p>Special cases for return values are as follows:
     * <ul>
     * <li>{@code <= 0} -> 1</li>
     * <li>{@code >= 2^30} -> 2^30</li>
     * </ul>
     */
    public static int safeFindNextPositivePowerOfTwo(final int value) {
        return value <= 0 ? 1 : value >= 0x40000000 ? 0x40000000 : findNextPositivePowerOfTwo(value);
    }

    /**
     * copy from netty {@see io.netty.util.internal.MathUtil#findNextPositivePowerOfTwo(int)}
     * Fast method of finding the next power of 2 greater than or equal to the supplied value.
     *
     * <p>If the value is {@code <= 0} then 1 will be returned.
     * This method is not suitable for {@link Integer#MIN_VALUE} or numbers greater than 2^30.
     *
     * @param value from which to search for next power of 2
     * @return The next power of 2 or the value itself if it is a power of 2
     */
    public static int findNextPositivePowerOfTwo(final int value) {
        assert value > Integer.MIN_VALUE && value < 0x40000000;
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }

    /**
     * 判断一个数是否是2的幂次方
     * @param value 目标值
     * @return true/false
     */
    public static boolean isPowerOfTwo(int value) {
        return (value & -value) == value;
    }
}
