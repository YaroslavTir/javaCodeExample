package ru.fls.privateoffice.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 06.04.12
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
@java.lang.annotation.Target({ElementType.METHOD})
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface EnableLogger {
}
