package com.free.winter.core.type.classreading;

import com.free.winter.core.annotation.AnnotationAttributes;
import com.free.winter.core.type.AnnotationMetadata;
import jdk.internal.org.objectweb.asm.Type;
import org.springframework.asm.AnnotationVisitor;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author weimin02
 * @date 2018/8/26
 * @project winter
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata {

    private final Set<String> annotationSet = new LinkedHashSet<>(4);
    private final Map<String, AnnotationAttributes> attributeMap = new HashMap<>();


    public AnnotationMetadataReadingVisitor() {
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        annotationSet.add(className);
        return null;
    }

    @Override
    public Set<String> getAnnotationTypes() {
        return annotationSet;
    }

    @Override
    public boolean hasAnnotation(String annotationType) {
        return annotationSet.contains(annotationType);
    }

    @Override
    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return attributeMap.get(annotationType);
    }
}
