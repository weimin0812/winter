package com.free.winter.beans.propertyeditor;

import com.free.winter.util.NumberUtils;
import com.free.winter.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * @author weimin02
 * @date 2018/8/2
 * @project winter
 */
public class CustomNumberEditor extends PropertyEditorSupport {
    private final Class<? extends Number> numberClass;
    private final NumberFormat numberFormat;
    private final boolean allowEmpty;

    /**
     * Constructs a <code>PropertyEditorSupport</code> object.
     *
     * @since 1.5
     */
    public CustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty)
    throws IllegalArgumentException{

        if (numberClass == null || !Number.class.isAssignableFrom(numberClass)){
            throw new IllegalArgumentException("Property class must be a subclass of Number");
        }

        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    /**
     * Constructs a <code>PropertyEditorSupport</code> object.
     *
     * @since 1.5
     */
    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty)
    throws IllegalArgumentException{
        this(numberClass, null, allowEmpty);
    }

    /**
     * Sets the property value by parsing a given String.  May raise
     * java.lang.IllegalArgumentException if either the String is
     * badly formatted or if this kind of property can't be expressed
     * as text.
     *
     * @param text The string to be parsed.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (allowEmpty && !StringUtils.hasText(text)){
            setValue(null);
        } else if (numberFormat != null){
            setValue(NumberUtils.parseNumber(text, numberClass, numberFormat));
        } else{
            setValue(NumberUtils.parseNumber(text, numberClass));
        }
    }

    /**
     * Set (or change) the object that is to be edited.
     *
     * @param value The new target object to be edited.  Note that this
     *              object should not be modified by the PropertyEditor, rather
     *              the PropertyEditor should create a new object to hold any
     *              modified value.
     */
    @Override
    public void setValue(Object value) {
        if (value instanceof Number){
            super.setValue(NumberUtils.convertNumberToTargetClass((Number)value, numberClass));

        }else{
            super.setValue(value);
        }

    }

    /**
     * Gets the property value as a string suitable for presentation
     * to a human to edit.
     *
     * @return The property value as a string suitable for presentation
     * to a human to edit.
     * <p>   Returns null if the value can't be expressed as a string.
     * <p>   If a non-null value is returned, then the PropertyEditor should
     * be prepared to parse that string back in setAsText().
     */
    @Override
    public String getAsText() {
        Object value = getValue();
        if (value == null){
            return "";
        }

        if (numberFormat != null){
            return numberFormat.format(value);
        }else{
            return value.toString();
        }
    }
}
