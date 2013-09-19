package com.squareup.shipfaster.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Documented @Target({ METHOD, FIELD}) @Retention(CLASS)
public @interface RegisterOnResume {
}