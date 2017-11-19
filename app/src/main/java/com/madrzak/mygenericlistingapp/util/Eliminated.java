package com.madrzak.mygenericlistingapp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Eliminated {

    boolean eliminateSerialization() default true;

    boolean eliminateDeserialization() default true;

}