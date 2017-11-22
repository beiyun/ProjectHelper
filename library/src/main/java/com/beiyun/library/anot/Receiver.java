package com.beiyun.library.anot;

import com.beiyun.library.entity.PostType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by beiyun on 2017/11/20.
 * 接收注解方法
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Receiver {

    PostType postType() default PostType.SINGLE;

}
