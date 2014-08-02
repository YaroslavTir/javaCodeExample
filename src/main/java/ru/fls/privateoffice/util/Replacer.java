package ru.fls.privateoffice.util;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 15.10.12
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public interface Replacer<T> {
    T replace(T replacingValue);
}
