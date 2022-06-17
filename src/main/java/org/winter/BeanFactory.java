package org.winter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    private final Map<String,Object> singletonObjects=new ConcurrentHashMap<String, Object>(256);

    private final Map<String,BeanDefinition> beanDefinitionMap=new HashMap<String, BeanDefinition>();

    public BeanFactory(){

    }

    public void registryBeanDefinition(String beanName,BeanDefinition beanDefinition){
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    public Object getBean(String beanName){
        Object bean=null;
        bean=singletonObjects.get(beanName);
        if(bean==null){
            bean=creatBean(beanName);
            populateBean(beanName,bean);
        }
        return bean;
    }

    public Object creatBean(String beanName){
        BeanDefinition bd=beanDefinitionMap.get(beanName);
        Object bean=null;
        try{
            Class<?> clazz=Class.forName(bd.getClassName());
            bean=clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }


    public void populateBean(String beanName,Object bean){
        BeanDefinition bd=beanDefinitionMap.get(beanName);
        List<Property> properties=bd.getProperties();
        Class<?> clazz=bean.getClass();
        for(Property property:properties){
            try{
                String name=property.getName();
                Object value=property.getValue();
                Field field=clazz.getDeclaredField(name);
                if(value instanceof BeanReference){
                    BeanReference beanReference=(BeanReference) value;
                    value=getBean(name);
                }
                field.setAccessible(true);
                field.set(bean,value);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        singletonObjects.put(beanName,bean);
    }
}
