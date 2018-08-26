package com.free.winter.version4.test;

import com.free.winter.core.annotation.AnnotationAttributes;
import com.free.winter.core.io.ClassPathResource;
import com.free.winter.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.free.winter.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

/**
 * @author weimin02
 * @date 2018/8/25
 * @project winter
 */
public class ClassReaderTest {
    @Test
    public void testGetClassMetaData() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("com/free/winter/version4/service/PetStoreService.class");
        ClassReader classReader = new ClassReader(classPathResource.getInputStream());
        ClassMetadataReadingVisitor classMetadataReadingVisitor = new ClassMetadataReadingVisitor();
        classReader.accept(classMetadataReadingVisitor, ClassReader.SKIP_DEBUG);

        Assert.assertFalse(classMetadataReadingVisitor.isAbstract());
        Assert.assertFalse(classMetadataReadingVisitor.isInterface());
        Assert.assertFalse(classMetadataReadingVisitor.isFinal());
        Assert.assertEquals("com.free.winter.version4.service.PetStoreService", classMetadataReadingVisitor.getClassName());
        Assert.assertEquals("java.lang.Object", classMetadataReadingVisitor.getSuperClassName());
        Assert.assertEquals(0, classMetadataReadingVisitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnotation() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/free/winter/version4/service/PetStoreService.class");
        ClassReader classReader = new ClassReader(resource.getInputStream());
        AnnotationMetadataReadingVisitor annotationMetadataReadingVisitor = new AnnotationMetadataReadingVisitor();

        classReader.accept(annotationMetadataReadingVisitor, ClassReader.SKIP_DEBUG);

        String annotation = "com.free.winter.stereotype.Component";
        Assert.assertTrue(annotationMetadataReadingVisitor.hasAnnotation(annotation));

        AnnotationAttributes annotationAttributes = annotationMetadataReadingVisitor.getAnnotationAttributes(annotation);

     //   Assert.assertTrue("petStore", annotationAttributes.get("value"));
    }
}
