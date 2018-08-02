package com.free.winter.beans.factory.xml;

import com.free.winter.beans.BeanDefinition;
import com.free.winter.beans.PropertyValue;
import com.free.winter.beans.factory.BeanDefinitionRegistry;
import com.free.winter.beans.factory.BeanDefinitionStoreException;
import com.free.winter.beans.factory.config.RunTimeBeanReference;
import com.free.winter.beans.factory.config.TypedStringValue;
import com.free.winter.beans.factory.support.GenericBeanDefinition;
import com.free.winter.core.io.Resource;
import com.free.winter.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author weimin02
 * @date 2018/7/30
 * @project winter
 */
public class XmlBeanDefinitionReader {
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String SCOPE_ATTRIBUTE = "scope";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String NAME_ATTRIBUTE = "name";

    BeanDefinitionRegistry beanDefinitionRegistry;

    protected final Log logger = LogFactory.getLog(getClass());

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream inputStream = null;

        try {
            inputStream = resource.getInputStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);

            Element root = document.getRootElement();
            Iterator<Element> elementIterator = root.elementIterator();

            while (elementIterator.hasNext()) {
                Element element = (Element) elementIterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClasssName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, beanClasssName);
                if (element.attributeValue(SCOPE_ATTRIBUTE) != null) {
                    beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }
                parsePropertyElement(element, beanDefinition);
                this.beanDefinitionRegistry.registerBeanDefinition(id, beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void parsePropertyElement(Element element, BeanDefinition beanDefinition) {
        Iterator iterator = element.elementIterator(PROPERTY_ELEMENT);

        while (iterator.hasNext()) {
            Element propertyElement = (Element) iterator.next();

            String propertyName = propertyElement.attributeValue(NAME_ATTRIBUTE);

            if (!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute ");
                return;
            }

            Object object = parsePropertyValue(propertyElement, beanDefinition, propertyName);
            PropertyValue propertyValue = new PropertyValue(propertyName, object);
            beanDefinition.getPropertyValues().add(propertyValue);
        }
    }


    private Object parsePropertyValue(Element propertyElement, BeanDefinition beanDefinition, String propertyName) {
        String elementName = propertyName == null ? "<property> element for property " + propertyName : "<constructor-arg> element";


        boolean hasRefAttribute = propertyElement.attribute(REF_ATTRIBUTE) != null;
        boolean hasValueAttribute = propertyElement.attribute(VALUE_ATTRIBUTE) != null;


        if (hasRefAttribute) {
            String refName = propertyElement.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty ref attribute");
            }
            RunTimeBeanReference runTimeBeanReference = new RunTimeBeanReference(refName);
            return runTimeBeanReference;
        } else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(propertyElement.attributeValue(VALUE_ATTRIBUTE));
            return valueHolder;
        } else {
            throw new RuntimeException(elementName + "must specify a ref or value");
        }


    }
}
