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

package mcaligares.modules.properties;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import mcaligares.modules.properties.bean.BeanWithProperties;
import mcaligares.modules.properties.config.PropertiesConfig;
import mcaligares.modules.properties.utils.PropertiesBuilder;

import org.junit.Test;

public class PropertiesTest {

    private static final String VALUE_AGE = "10";
    private static final String VALUE_NAME = "Hello!";
    private static final String PROPERTIES_AGE = "properties.age";
    private static final String PROPERTIES_NAME = "properties.name";
    private static final String PROPERTIES_FILE = "mcaligares/modules/properties/test.properties";

    @Test
    public void test() {
        BeanWithProperties beanWithProperties = new BeanWithProperties();
        assertThat(beanWithProperties.getAge(), nullValue());
        assertThat(beanWithProperties.getName(), nullValue());

        PropertiesConfig.build(beanWithProperties);

        assertThat(beanWithProperties.getAge(), notNullValue());
        assertThat(beanWithProperties.getName(), notNullValue());
        assertThat(beanWithProperties.getAge(), allOf(notNullValue(), is(Integer.class), is(Integer.valueOf(VALUE_AGE))));
        assertThat(beanWithProperties.getName(), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

    @Test
    public void createFromFileTest() throws IOException {
        Properties prop = PropertiesBuilder.createFromFile(PROPERTIES_FILE);
        assertThat(prop, notNullValue());
        assertThat(prop.get(PROPERTIES_AGE), allOf(notNullValue(), is(String.class), is(VALUE_AGE)));
        assertThat(prop.get(PROPERTIES_NAME), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

    @Test
    public void createFromMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(PROPERTIES_AGE, VALUE_AGE);
        map.put(PROPERTIES_NAME, VALUE_NAME);
        Properties prop = PropertiesBuilder.createFromMap(map);
        assertThat(prop, notNullValue());
        assertThat(prop.get(PROPERTIES_AGE), allOf(notNullValue(), is(String.class), is(VALUE_AGE)));
        assertThat(prop.get(PROPERTIES_NAME), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

    @Test
    public void createFromArray() {
        Object[][] keysValues = new Object[][] {
                {PROPERTIES_AGE, VALUE_AGE},
                {PROPERTIES_NAME, VALUE_NAME}
        };
        Properties prop = PropertiesBuilder.createFromArray(keysValues);
        assertThat(prop, notNullValue());
        assertThat(prop.get(PROPERTIES_AGE), allOf(notNullValue(), is(String.class), is(VALUE_AGE)));
        assertThat(prop.get(PROPERTIES_NAME), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

    @Test
    public void buildFromSingleKeyAndValue() {
        Properties prop = new PropertiesBuilder().addKey(PROPERTIES_AGE).addValue(VALUE_AGE)
                .addKey(PROPERTIES_NAME).addValue(VALUE_NAME).build();
        assertThat(prop, notNullValue());
        assertThat(prop.get(PROPERTIES_AGE), allOf(notNullValue(), is(String.class), is(VALUE_AGE)));
        assertThat(prop.get(PROPERTIES_NAME), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

    @Test
    public void buildFromKeysAndValues() {
        Properties prop = new PropertiesBuilder()
                .addKeys(PROPERTIES_AGE, PROPERTIES_NAME)
                .addValues(VALUE_AGE,VALUE_NAME).build();
        assertThat(prop, notNullValue());
        assertThat(prop.get(PROPERTIES_AGE), allOf(notNullValue(), is(String.class), is(VALUE_AGE)));
        assertThat(prop.get(PROPERTIES_NAME), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

    @Test
    public void buildFromKeysAndValuesList() {
        List<String> keys = new ArrayList<String>();
        keys.add(PROPERTIES_AGE);
        keys.add(PROPERTIES_NAME);
        List<String> values = new ArrayList<String>();
        values.add(VALUE_AGE);
        values.add(VALUE_NAME);

        Properties prop = new PropertiesBuilder().addKeys(keys).addValues(values).build();
        assertThat(prop, notNullValue());
        assertThat(prop.get(PROPERTIES_AGE), allOf(notNullValue(), is(String.class), is(VALUE_AGE)));
        assertThat(prop.get(PROPERTIES_NAME), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

    @Test
    public void buildFromeyValue() {
        Properties prop = new PropertiesBuilder()
                .addKeyValue(PROPERTIES_AGE, VALUE_AGE)
                .addKeyValue(PROPERTIES_NAME, VALUE_NAME).build();
        assertThat(prop, notNullValue());
        assertThat(prop.get(PROPERTIES_AGE), allOf(notNullValue(), is(String.class), is(VALUE_AGE)));
        assertThat(prop.get(PROPERTIES_NAME), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

    @Test
    public void buildFromKeysValues() {
        Object[] keys = new Object[] {PROPERTIES_AGE, PROPERTIES_NAME};
        Object[] values = new Object[] {VALUE_AGE, VALUE_NAME};
        Properties prop = new PropertiesBuilder().addKeysValues(keys, values).build();
        assertThat(prop, notNullValue());
        assertThat(prop.get(PROPERTIES_AGE), allOf(notNullValue(), is(String.class), is(VALUE_AGE)));
        assertThat(prop.get(PROPERTIES_NAME), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

    @Test
    public void buildFromKeysValuesList() {

        List<String> keys = new ArrayList<String>();
        keys.add(PROPERTIES_AGE);
        keys.add(PROPERTIES_NAME);
        List<String> values = new ArrayList<String>();
        values.add(VALUE_AGE);
        values.add(VALUE_NAME);

        Properties prop = new PropertiesBuilder().addKeysValues(keys, values).build();
        assertThat(prop, notNullValue());
        assertThat(prop.get(PROPERTIES_AGE), allOf(notNullValue(), is(String.class), is(VALUE_AGE)));
        assertThat(prop.get(PROPERTIES_NAME), allOf(notNullValue(), is(String.class), is(VALUE_NAME)));
    }

}
