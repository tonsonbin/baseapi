package com.tyfo.app.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 树状图
 */
public class TreeUtils {

    /**
     * @param list               树数据
     * @param root               根节点
     * @param keyFieldName       关联属性
     * @param parentKeyFieldName 关联父属性
     * @param subFieldName       子节点数据
     * @param <T>                根节点
     */
    public static <T> void createTree(List<T> list, T root, String keyFieldName, String parentKeyFieldName,
                                      String subFieldName) {
        Field keyField = ReflectUtils.getField(keyFieldName, root);
        Field parentKeyField = ReflectUtils.getField(parentKeyFieldName, root);
        Field subField = ReflectUtils.getField(subFieldName, root);
        find(list, root, keyField, parentKeyField, subField);
    }

    /**
     * 根据父节点的关联值 查找
     */
    public static <T, E> List<E> getKeys(List<T> list, T root, String keyFieldName, String parentKeyFieldName) {
        Field keyField = ReflectUtils.getField(keyFieldName, root);
        Field parentKeyField = ReflectUtils.getField(parentKeyFieldName, root);
        List<E> keys = new ArrayList<>();
        E value = ReflectUtils.getValueByGetMethod(keyField, root);
        keys.add(value);
        findKeys(list, keys, root, keyField, parentKeyField);
        return keys;
    }

    private static <T> void find(List<T> list, T parent, Field keyField, Field parentKeyField, Field subField) {
        List<T> subs = getSubs(list, parent, keyField, parentKeyField);

        if (subs != null) {
            ReflectUtils.setValueByField(subField, parent, subs);
            for (T sub : subs) {
                //递归找子节点
                find(list, sub, keyField, parentKeyField, subField);
            }
        }
    }

    private static <T, E> List<E> findKeys(List<T> list, List<E> keys, T parent, Field keyField, Field parentKeyField) {
        List<T> subs = getSubs(list, parent, keyField, parentKeyField);

        List<E> subKeys = getSubKeys(list, parent, keyField, parentKeyField);
        if (subs != null) {
            keys.addAll(subKeys);
            for (T sub : subs) {
                //递归找子节点
                findKeys(list, keys, sub, keyField, parentKeyField);
            }
        }
        return keys;
    }


    private static <T> List<T> getSubs(List<T> list, T parent, Field keyField, Field parentKeyField) {
        List<T> subs = null;
        for (T t : list) {
            Object keyFieldValue = ReflectUtils.getValueByField(keyField, parent);
            Object parentFieldValue = ReflectUtils.getValueByField(parentKeyField, t);
            if (keyFieldValue.equals(parentFieldValue)) {
                if (subs == null) {
                    subs = new ArrayList<>();
                }
                subs.add(t);
            }
        }
        return subs;
    }


    private static <T, E> List<E> getSubKeys(List<T> list, T parent, Field keyField, Field parentKeyField) {
        List<E> subs = null;
        for (T t : list) {
            Object keyFieldValue = ReflectUtils.getValueByField(keyField, parent); //父节点key
            Object parentFieldValue = ReflectUtils.getValueByField(parentKeyField, t); //根结点关联的key
            if (keyFieldValue.equals(parentFieldValue)) { //关联字段相等
                if (subs == null) {
                    subs = new ArrayList<>();
                }
                //取子节点key
                Object key = ReflectUtils.getValueByField(keyField, t);
                subs.add((E) key);
            }
        }
        return subs;
    }
}