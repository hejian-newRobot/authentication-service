package com.service.auth.serviceauth.util;

import java.util.Arrays;

/**
 * 项目名称：mns-server
 * 包名称:util
 * 类描述：
 * 创建人：何健
 * 创建时间：2019-02-26 09:29
 * 修改人：何健
 * 修改时间：2019-02-26 09:29
 * 修改备注：
 *
 * @author hejian
 */
public final class StringUtil {

    public static final String EMPTY = "";

    /**
     * @param target 需要被判断的字符串
     * @return 如果@{code target}是null或者是空串则返回true ，反之则返回false
     */
    public static boolean isNullOrEmpty(String target) {
        return target == null || "".equals(target.trim());
    }

    public static boolean isNullOrEmptyAll(String... targets) {
        return Arrays.stream(targets).allMatch(StringUtil::isNullOrEmpty);
    }

    public static boolean isNullOrEmptyAny(String... targets) {
        return Arrays.stream(targets).anyMatch(StringUtil::isNullOrEmpty);
    }

    public static boolean isEmptyNonNull(String target) {
        return target != null && "".equals(target.trim());
    }

    public static boolean isEmptyNonNull(Object target) {
        String value = valueOf(target);
        return target != null && "".equals(value.trim());
    }

    public static String valueOf(Object valueCasted) {
        if (valueCasted == null) {
            return EMPTY;
        }
        return valueCasted.toString();
    }

    public static String valueOf(String text, String defaultValue) {
        return text == null ? defaultValue : text;
    }

    public static String emptyToNull(String str) {
        if (isEmptyNonNull(str)) {
            return null;
        }
        return str;
    }

    public static String emptyToNull(Object obj) {
        String str = valueOf(obj);
        return emptyToNull(str);
    }

    /**
     * 检查字符串结束是否有 . 字符，有则直接返回原字符串，没有往尾部追加 .字符
     *
     * @param str 目标字符串
     * @return 返回结果字符串
     */
    public static String checkPointAtLast(String str) {
        if (isNullOrEmpty(str)) {
            return EMPTY;
        }
        return str.endsWith("\\.") ? str : str + ".";
    }

    /**
     * 检查文件路径的结束是否有目录分割符 "/" 没有则追加分割符 有则直接返回
     *
     * @param path 文件路径
     * @return 返回检查结果 {@code String}
     */
    public static String checkSlashAtLast(String path) {
        if (path == null) {
            return StringUtil.EMPTY;
        }
        return path.endsWith("/") ? path : path + "/";
    }
}
