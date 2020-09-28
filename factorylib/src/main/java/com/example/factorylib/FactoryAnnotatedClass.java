package com.example.factorylib;

import com.example.annotation.AnFactory;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

/**
 * @author tangqipeng
 * @date 2020/9/23 5:52 PM
 * @email tangqipeng@aograph.com
 */
public class FactoryAnnotatedClass {
    private TypeElement mAnnotatedClassElement;
    private String mQualifiedSuperClassName;
    private String mSimpleTypeName;
    private String mId;

    public FactoryAnnotatedClass(TypeElement classElement) {
        this.mAnnotatedClassElement = classElement;
        AnFactory annotation = classElement.getAnnotation(AnFactory.class);
        mId = annotation.id();
        if (mId.length() == 0) {
            throw new IllegalArgumentException(
                    String.format("id() in @%s for class %s is null or empty! that's not allowed",
                            AnFactory.class.getSimpleName(), classElement.getQualifiedName().toString()));
        }
        // 获取注解类
        try {
            Class<?> clazz = annotation.type();
            mQualifiedSuperClassName = clazz.getCanonicalName();
            mSimpleTypeName = clazz.getSimpleName();
        } catch (MirroredTypeException mte) {
            DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
            TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
            mQualifiedSuperClassName = classTypeElement.getQualifiedName().toString();
            mSimpleTypeName = classTypeElement.getSimpleName().toString();
        }
        System.out.println("mQualifiedSuperClassName is "+mQualifiedSuperClassName);
        System.out.println("mSimpleTypeName is "+mSimpleTypeName);
    }

    public TypeElement getAnnotatedClassElement() {
        return mAnnotatedClassElement;
    }

    public String getQualifiedSuperClassName() {
        return mQualifiedSuperClassName;
    }

    public String getSimpleTypeName() {
        return mSimpleTypeName;
    }

    public String getId() {
        return mId;
    }
}
