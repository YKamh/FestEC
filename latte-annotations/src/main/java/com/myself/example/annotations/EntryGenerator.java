package com.myself.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Kamh on 2018/2/8.
 */

@Target(ElementType.TYPE)//这个注解告诉编译器我们这个注解使用在类上面的
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    String packageName();

    Class<?> entryTemplete();
}
