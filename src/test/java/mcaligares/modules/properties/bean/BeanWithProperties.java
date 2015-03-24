package mcaligares.modules.properties.bean;

import mcaligares.modules.properties.annotation.BeanProperties;
import mcaligares.modules.properties.annotation.Key;

@BeanProperties(prop = "mcaligares/modules/properties/test.properties")
public class BeanWithProperties {

    @Key("properties.name")
    private String name;

    @Key("properties.age")
    private Integer age;

    public BeanWithProperties() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
