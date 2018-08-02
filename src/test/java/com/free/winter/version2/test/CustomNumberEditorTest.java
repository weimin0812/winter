package com.free.winter.version2.test;

import com.free.winter.beans.propertyeditor.CustomNumberEditor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author weimin02
 * @date 2018/8/1
 * @project winter
 */
public class CustomNumberEditorTest {
    @Test
    public void testConvertString(){
        CustomNumberEditor customNumberEditor = new CustomNumberEditor(Integer.class, true);
        customNumberEditor.setAsText("3");
        Object value = customNumberEditor.getValue();
        Assert.assertTrue(value instanceof Integer);
        Assert.assertEquals(3, ((Integer)value).intValue());

        customNumberEditor.setAsText("");
        Assert.assertTrue(customNumberEditor.getValue() == null);

        try{
            customNumberEditor.setAsText("3.1");
        } catch (IllegalArgumentException e){
            return;
        }

        Assert.fail();





    }
}
