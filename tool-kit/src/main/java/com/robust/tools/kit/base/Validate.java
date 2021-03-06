package com.robust.tools.kit.base;

import com.robust.tools.kit.base.annotation.Nullable;

/**
 * @Description: 参数校验统一使用 {@link org.apache.commons.lang3.Validate}, 补充一些缺少的.
 * 为什么不用Guava的{@link com.google.common.base.Preconditions} , 一是少打几个字而已, 二是Validate的方法多，比如noNullElements()判断多个元素都不为空
 * 目前主要参考 {@link com.google.common.math.MathPreconditions} , 补充数字为正数或非负数的校验
 * @Author: robust
 * @CreateDate: 2019/7/22 17:49
 * @Version: 1.0
 */
public class Validate extends org.apache.commons.lang3.Validate {
    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static int positive(@Nullable String role, int x) {
        if (x <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static Integer positive(@Nullable String role, Integer x) {
        if (x.intValue() <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static long positive(@Nullable String role, long x) {
        if (x <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static Long positive(@Nullable String role, Long x) {
        if (x.longValue() <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    /**
     * 校验为正数则返回该数字，否则抛出异常.
     */
    public static double positive(@Nullable String role, double x) {
        if (!(x > 0)) { // not x < 0, to work with NaN.
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static int nonNegative(@Nullable String role, int x) {
        if (x < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static Integer nonNegative(@Nullable String role, Integer x) {
        if (x.intValue() < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static long nonNegative(@Nullable String role, long x) {
        if (x < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static Long nonNegative(@Nullable String role, Long x) {
        if (x.longValue() < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    /**
     * 校验为非负数则返回该数字，否则抛出异常.
     */
    public static double nonNegative(@Nullable String role, double x) {
        if (!(x >= 0)) { // not x < 0, to work with NaN.
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }
}
