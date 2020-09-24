package com.tqp.plugin

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.ClassVisitor

/**
 * @author tangqipeng* @date 2020/9/24 3:35 PM
 * @email tangqipeng@aograph.com
 */
class MyClassVisitor extends ClassVisitor {

    String className

    MyClassVisitor(ClassVisitor var2) {
        super(Opcodes.ASM7, var2)
    }

    @Override
    void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces)
        println("name:" + name + " superName:" + superName + " signature:" + signature + " interfaces:" + interfaces);
        className = name
    }

    @Override
    AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return super.visitAnnotation(descriptor, visible)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//        return super.visitMethod(access, name, descriptor, signature, exceptions)
        MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        return new MyMethedVisitorAdapter(api, visitor, access, name, descriptor)
    }

    @Override
    void visitEnd() {
        super.visitEnd()
    }
}
