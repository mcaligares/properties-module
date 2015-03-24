/*
 * Copyright 2015 Miguel Augusto Caligares <mcaligares@gmail.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mcaligares.modules.properties.config;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

import mcaligares.modules.properties.annotation.BeanProperties;
import mcaligares.modules.properties.annotation.Key;
import mcaligares.modules.properties.exception.PropertiesException;
import mcaligares.utils.reflections.ReflectionUtils;

public final class PropertiesConfig {

    public static final void build(final Object entity) {
        // Checking if object have a BeanProperties annotation class
        if (entity == null || !ReflectionUtils.hasAnnotationClass(entity, BeanProperties.class)) return;

        // Get the Bean Properties
        BeanProperties beanProperties = entity.getClass().getAnnotation(BeanProperties.class);

        // Get all field with Key annotation
        List<Field> fields = ReflectionUtils.getFieldsWithAnnotations(entity.getClass(), Key.class);
        if (fields == null || fields.isEmpty()) return;

        // Load the properties file
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(beanProperties.prop()));
        } catch (Exception e) {
            throw new PropertiesException("Error loading property file", e);
        }

        // Checking for properties
        if (properties == null || properties.isEmpty()) return;

        for (Field field : fields) {
            Key key = field.getAnnotation(Key.class);
            // Checking if properties contains key
            if (key == null || !properties.containsKey(key.value())) continue;

            // Setting field value
            Object value = wrapperValue(properties.getProperty(key.value()), field.getType());
            ReflectionUtils.setValue(entity, value, field);
        }

    }

    private static final Object wrapperValue(String value, Class<?> clazz) {
        if (clazz == null || value == null) return null;

        //TODO add wrapper for array, list and map? maybe?
        if (clazz.equals(String.class)) {
            return value;
        } else if (clazz.equals(Long.class)) {
            return Long.valueOf(value);
        } else if (clazz.equals(Integer.class)) {
            return Integer.valueOf(value);
        } else if (clazz.equals(Double.class)) {
            return Double.valueOf(value);
        } else if (clazz.equals(Float.class)) {
            return Float.valueOf(value);
        } else if (clazz.equals(Short.class)) {
            return Short.valueOf(value);
        } else if (clazz.equals(Boolean.class)) {
            return Boolean.valueOf(value);
        } else if (clazz.equals(Byte.class)) {
            return Byte.valueOf(value);
        } else if (clazz.equals(Character.class)) {
            return Character.valueOf(value.charAt(0));
        } else if (clazz.equals(CharSequence.class)) {
            return value;
        } else {
            return value;
        }
    }
}
