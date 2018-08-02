package com.free.winter.version2.test;

import com.free.winter.beans.SimpleTypeConverter;
import com.free.winter.beans.TypeConverter;
import com.free.winter.beans.TypeMismatchException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author weimin02
 * @date 2018/8/2
 * @project winter
 */
public class TypeConverterTest {
    @Test
    public void testConvertStringToInt(){
        TypeConverter typeConverter = new SimpleTypeConverter();
        Integer i = typeConverter.convertIfNecessary("3", Integer.class);
        Assert.assertEquals(3, i.intValue());

        try{
            typeConverter.convertIfNecessary("3.888", Integer.class);
        } catch (TypeMismatchException e){
            return;
        }
        fail();
    }

    @Test
    public void testConvertStringToBoolean(){
        TypeConverter typeConverter = new SimpleTypeConverter();
        Boolean b = typeConverter.convertIfNecessary("true", Boolean.class);
        Assert.assertEquals(true, b.booleanValue());

        try{
            typeConverter.convertIfNecessary("xxxyyyz", Integer.class);
        } catch (TypeMismatchException e){
            return;
        }
        fail();
    }


}
