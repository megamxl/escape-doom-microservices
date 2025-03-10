package at.escapedoom.data.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static void copyNonNullProperties(Object source, Object target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Source and target objects must not be null");
        }

        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();
        Field[] targetFields = targetClass.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            for (Field targetField : targetFields) {
                targetField.setAccessible(true);

                if (sourceField.getName().equals(targetField.getName())
                        && sourceField.getType().equals(targetField.getType())) {
                    try {
                        Object value = targetField.get(target);
                        // Avoid overwriting with null values
                        if (value != null) {
                            sourceField.set(source, value);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to merge fields", e);
                    }
                }
            }
        }
    }
}
