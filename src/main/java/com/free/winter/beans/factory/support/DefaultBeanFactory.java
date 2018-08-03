package com.free.winter.beans.factory.support;

import com.free.winter.beans.BeanDefinition;
import com.free.winter.beans.PropertyValue;
import com.free.winter.beans.SimpleTypeConverter;
import com.free.winter.beans.factory.BeanCreationException;
import com.free.winter.beans.factory.BeanDefinitionRegistry;
import com.free.winter.beans.factory.config.ConfigurableBeanFactory;
import com.free.winter.util.ClassUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weimin02
 * @date 2018/7/28
 * @project winter
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements BeanDefinitionRegistry, ConfigurableBeanFactory {
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ClassLoader classLoader;

    public DefaultBeanFactory() {

    }

    @Override
    public void registerBeanDefinition(String id, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(id, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanID) {
        return this.beanDefinitionMap.get(beanID);
    }

    @Override
    public Object getBean(String beanID) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanID);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }

        if (beanDefinition.isSingleton()) {
            Object bean = getSingleton(beanID);
            if (bean == null) {
                bean = createBean(beanDefinition);
                registerSingleton(beanID, bean);
            }

            return bean;
        }

        return createBean(beanDefinition);
    }


    private Object createBean(BeanDefinition beanDefinition) {
        Object bean = instantiateBean(beanDefinition);

        //     populateBean(beanDefinition, bean);
        populateBeanUseCommonBeanUtils(beanDefinition, bean);
        return bean;
    }

    private void populateBean(BeanDefinition beanDefinition, Object bean) {
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();
        if (propertyValueList == null || propertyValueList.size() == 0) {
            return;
        }

        BeanDefinitionValueResolver beanDefinitionValueResolver = new BeanDefinitionValueResolver(this);
        SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();


        try {
            for (PropertyValue propertyValue : propertyValueList) {
                String propertyName = propertyValue.getName();
                Object originalValue = propertyValue.getValue();
                Object resolvedValue = beanDefinitionValueResolver.resolveValueIfNecessary(originalValue);

                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getName().equals(propertyName)) {
                        Object convertValue = simpleTypeConverter.convertIfNecessary(resolvedValue, propertyDescriptor.getPropertyType());
                        propertyDescriptor.getWriteMethod().invoke(bean, convertValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException("Fail to obtain BeanInfo for class " + beanDefinition.getBeanClassName());
        }
    }


    /**
     * 使用BeanUtils.setProperty进行注入属性
     *
     * @param beanDefinition
     * @param bean
     */
    private void populateBeanUseCommonBeanUtils(BeanDefinition beanDefinition, Object bean) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();

        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }

        BeanDefinitionValueResolver beanDefinitionValueResolver = new BeanDefinitionValueResolver(this);
        try {
            for (PropertyValue propertyValue : propertyValues) {
                String propertyName = propertyValue.getName();
                Object originalValue = propertyValue.getValue();

                Object resolveValue = beanDefinitionValueResolver.resolveValueIfNecessary(originalValue);
                BeanUtils.setProperty(bean, propertyName, resolveValue);
            }
        } catch (Exception e) {
            throw new BeanCreationException("Fail to obtain BeanInfo for class " + beanDefinition.getBeanClassName());
        }
    }

    private Object instantiateBean(BeanDefinition beanDefinition) {
        ClassLoader classLoader = this.getBeanClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();

        try {
            Class<?> aClass = classLoader.loadClass(beanClassName);
            return aClass.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed");
        }
    }


    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.classLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }
}

