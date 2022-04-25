package com.klok.api.util;

import java.lang.reflect.Field;

public class NullAwareBeanUtilsBean {

	public static void copyDiff(Object destination, Object source) throws IllegalAccessException, NoSuchFieldException {
		for (Field field : source.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String name = field.getName();
			Object value = field.get(source);

			if (null != value) {

				Field destField = destination.getClass().getDeclaredField(name);
				destField.setAccessible(true);

				destField.set(destination, value);
			}
		}
	}

}
