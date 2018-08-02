package com.free.winter.version2.test;

import com.free.winter.beans.propertyeditor.CustomBooleanEditor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author weimin02
 * @date 2018/8/2
 * @project winter
 */
public class CustomBooleanEditorTest {
    @Test
    public void testConvertStringToBoolean() {
        CustomBooleanEditor customBooleanEditor = new CustomBooleanEditor(true);

        customBooleanEditor.setAsText("true");
        Assert.assertEquals(true, ((Boolean) customBooleanEditor.getValue()).booleanValue());
        customBooleanEditor.setAsText("false");
        Assert.assertEquals(false, ((Boolean) customBooleanEditor.getValue()).booleanValue());

        customBooleanEditor.setAsText("on");
        Assert.assertEquals(true, ((Boolean) customBooleanEditor.getValue()).booleanValue());
        customBooleanEditor.setAsText("off");
        Assert.assertEquals(false, ((Boolean) customBooleanEditor.getValue()).booleanValue());

        customBooleanEditor.setAsText("yes");
        Assert.assertEquals(true, ((Boolean) customBooleanEditor.getValue()).booleanValue());
        customBooleanEditor.setAsText("no");
        Assert.assertEquals(false, ((Boolean) customBooleanEditor.getValue()).booleanValue());

        try {
            customBooleanEditor.setAsText("aabbcc");
        } catch (IllegalArgumentException e) {
            return;
        }

        Assert.fail();

    }
}
