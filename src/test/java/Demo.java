import org.winter.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Demo {


    public static void main(String[] args) {
        BeanFactory beanFactory=new BeanFactory();

        List<Property> properties=new ArrayList<Property>();
        properties.add(new Property("age",10));
        properties.add(new Property("name","Dios"));
        properties.add(new Property("dog",new BeanReference("dog")));
        BeanDefinition beanDefinition=new BeanDefinition("org.winter.People",properties);
        beanFactory.registryBeanDefinition("people",beanDefinition);

        List<Property> dogproperties=new ArrayList<Property>();
        dogproperties.add(new Property("age",2));
        dogproperties.add(new Property("name","Smith"));
        BeanDefinition dogbd=new BeanDefinition("org.winter.Dog",dogproperties);
        beanFactory.registryBeanDefinition("dog",dogbd);

        People people=(People)beanFactory.getBean("people");
        System.out.println(people);

        Dog dog=(Dog)beanFactory.getBean("dog");
        System.out.println(dog);
    }
}
