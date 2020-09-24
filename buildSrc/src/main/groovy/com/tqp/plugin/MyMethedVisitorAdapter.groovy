package com.tqp.plugin

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * @author tangqipeng* @date 2020/9/24 3:53 PM
 * @email tangqipeng@aograph.com
 */
class MyMethedVisitorAdapter extends AdviceAdapter {

    String className
    //是否含有某个注解
    boolean mInject

    MyMethedVisitorAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor)
        println("name is "+name)
        println("descriptor is "+descriptor)
        this.className = name
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter()
        if (mInject) {
            mv.visitLdcInsn(className + " -> TAG");
            mv.visitLdcInsn("\u5f00\u59cb\u65f6\u95f4:" + System.currentTimeMillis());
            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(POP);
        }
    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode)
        if (mInject) {
            mv.visitLdcInsn(className + " -> TAG");
            mv.visitLdcInsn("\u7ed3\u675f\u65f6\u95f4:" + System.currentTimeMillis());
            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(POP);
        }
    }

    @Override
    AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
//        if (Type.getDescriptor(Check).equals(descriptor)){
//            mInject = true
//        } else {
//            mInject = false
//        }
        println('descriptor is '+descriptor)
        mInject = descriptor.equals("Lcom/example/annotationlib/Check;")
        return super.visitAnnotation(descriptor, visible)
    }
}
