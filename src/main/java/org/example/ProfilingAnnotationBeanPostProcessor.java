package org.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> beanNameToBean = new HashMap<>();

    private final ProfilingController controller = new ProfilingController();

    public ProfilingAnnotationBeanPostProcessor() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("profiling", "name", controller.getClass().getSimpleName());
        mBeanServer.registerMBean(controller, objectName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            beanNameToBean.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = beanNameToBean.get(beanName);
        if (beanClass != null) {
            ClassLoader classLoader = beanClass.getClassLoader();
            Class<?>[] interfaces = beanClass.getInterfaces();
            return Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
                if (controller.isEnabled()) {
                    System.out.println("Starting profiling...");
                    long start = System.nanoTime();
                    Object result = method.invoke(bean, args);
                    long end = System.nanoTime();
                    System.out.println("Stop profiling: " + (end - start) + "ns");
                    return result;
                }
                return method.invoke(bean, args);
            });
        }
        return bean;
    }
}
