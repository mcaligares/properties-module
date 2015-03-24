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

package mcaligares.modules.properties.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author miguel
 *
 */
public final class PropertiesBuilder {
    private List<Object> keys;
    private List<Object> values;

    public PropertiesBuilder() {
        super();
    }

    private void addKeyElem(Object elem) {
        if (keys == null) keys = new ArrayList<Object>();
        keys.add(elem);
    }

    private void addKeyElem(Collection<?> elems) {
        if (keys == null) keys = new ArrayList<Object>();
        keys.addAll(elems);
    }

    private void addValueElem(Object elem) {
        if (values == null) values = new ArrayList<Object>();
        values.add(elem);
    }

    private void addValueElem(Collection<?> elem) {
        if (values == null) values = new ArrayList<Object>();
        values.addAll(elem);
    }

    public void clear() {
        clearKeys();
        clearvalues();
    }

    public void clearKeys() {
        if (keys != null) keys.clear();
    }

    public void clearvalues() {
        if (values != null) values.clear();
    }

    public Properties build() {
        Properties prop = new Properties();
        if (keys == null || keys.isEmpty()) return prop;

        for (int i = 0; i < keys.size(); i++) {
            prop.put(keys.get(i), values.size() - 1 >= i ? values.get(i) : null);
        }

        return prop;
    }

    public PropertiesBuilder addKey(Object key) {
        if (key != null) addKeyElem(key);
        return this;
    }

    public PropertiesBuilder addKeys(Object ... keys) {
        if (keys != null && keys.length > 0) addKeyElem(Arrays.asList(keys));
        return this;
    }

    public <T> PropertiesBuilder addKeys(List<T> keys) {
        if (keys != null && !keys.isEmpty()) addKeyElem(keys);
        return this;
    }

    public PropertiesBuilder addValue(Object value) {
        if (value != null) addValueElem(value);
        return this;
    }

    public PropertiesBuilder addValues(Object ... values) {
        if (values != null && values.length > 0) addValueElem(Arrays.asList(values));
        return this;
    }

    public <T> PropertiesBuilder addValues(List<T> values) {
        if (values != null && !values.isEmpty()) addValueElem(values);
        return this;
    }

    public PropertiesBuilder addKeyValue(Object key, Object value) {
        addKeyElem(key);
        addValueElem(value);
        return this;
    }

    public PropertiesBuilder addKeysValues(Object[] keys, Object[] values) {
        addKeyElem(Arrays.asList(keys));
        addValueElem(Arrays.asList(values));
        return this;
    }

    public <T> PropertiesBuilder addKeysValues(List<T> keys, List<T> values) {
        addKeyElem(keys);
        addValueElem(values);
        return this;
    }

    public static Properties createFromFile(String pathname) throws IOException {
        if (pathname == null || pathname.trim().length() < 1) return null;

        Properties prop = new Properties();
        prop.load(ClassLoader.getSystemResourceAsStream(pathname));
        return prop;
    }

    public static final <K,V> Properties createFromMap(Map<? extends K, ? extends V> maps) {
        if (maps == null) return null;

        Properties prop = new Properties();
        prop.putAll(maps);
        return prop;
    }

    public static final <T> Properties createFromArray(T[][] ... arrays) {
        if (arrays == null || arrays.length < 1) return null;

        Properties prop = null;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null || arrays[i].length == 0) continue;

            for (int j = 0; j < arrays[i].length; j++) {
                if (arrays[i][j] == null || arrays[i][j].length == 0) continue;

                if (prop == null) prop = new Properties();

                Object key = arrays[i][j][0];
                Object value = arrays[i][j].length >= 2 ? arrays[i][j][1] != null ? arrays[i][j][1] : "" : "";
                prop.put(key, value);

            }
        }
        return prop;
    }

}
