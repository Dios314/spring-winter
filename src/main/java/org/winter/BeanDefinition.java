package org.winter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


public class BeanDefinition {

    private String className;

    private List<Property> properties;

    public BeanDefinition(String className){
        this.className=className;
        properties=new ArrayList<Property>();
    }

    public BeanDefinition(String className,List<Property> properties){
        this.className=className;
        this.properties=properties;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
