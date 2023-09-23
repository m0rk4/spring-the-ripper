package org.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {

    /**
     * Don't do proxying here!
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if (annotation != null) {
                int min = annotation.min();
                int max = annotation.max();
                int random = ThreadLocalRandom.current().nextInt(min, max + 1);
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, random);
                field.setAccessible(false);
            }
        }
        return bean;
    }

    /**
     * Do proxying/replacements here!
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
