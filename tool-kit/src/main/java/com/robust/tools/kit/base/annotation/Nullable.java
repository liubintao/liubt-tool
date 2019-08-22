package com.robust.tools.kit.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Description: 标注参数、字段、方法可为空
 * @Author: robust
 * @CreateDate: 2019/7/19 11:46
 * @Version: 1.0
 */
@Target({ElementType.PARAMETER,ElementType.FIELD, ElementType.METHOD})
public @interface Nullable {
}
