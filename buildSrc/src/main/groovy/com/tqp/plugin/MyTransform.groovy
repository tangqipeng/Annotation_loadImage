package com.tqp.plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

/**
 * @author tangqipeng* @date 2020/9/24 2:40 PM
 * @email tangqipeng@aograph.com
 */
class MyTransform extends Transform {


    @Override
    String getName() {
        return MyTransform.simpleName
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    /**
     * 是否支持增量编译
     * @return
     */
    @Override
    boolean isIncremental() {
        return false
    }

    //临时文件集合
    List<File> mTemporaryFiles = new ArrayList<>()

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        // inputs中是传过来的输入流，其中有两种格式，一种是jar包格式一种是目录格式。
        Collection<TransformInput> inputs = transformInvocation.inputs
        // 获取到输出目录，最后将修改的文件复制到输出目录，这一步必须做不然编译会报错
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        //循环遍历输入流
        for (TransformInput input : inputs){
            //处理jar中的class文件
            for (JarInput jarInput:input.jarInputs){
                File dest = outputProvider.getContentLocation(jarInput.name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }
            //处理文件目录下的class文件
            for (DirectoryInput directoryInput : input.directoryInputs){
                File dir = directoryInput.file
                //判断是否为目录
                if (dir.isDirectory()){
                    mTemporaryFiles.clear()
                    findFiles(dir)
                    for (File file : mTemporaryFiles){
                        processingFile(file)
                    }
                }else if (dir.isFile()){
                    processingFile(dir)
                }else{
                    return
                }
                File dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file, dest)
            }
        }
    }

    def findFiles(File dir){
        File[] files = dir.listFiles()
        for (File file : files){
            if (file.isDirectory()){
                findFiles(file)
            }else if (file.isFile()){
                mTemporaryFiles.add(file)
            }
        }
    }

    def processingFile(File file) throws IOException {
        String fileName = file.name
        if (checkClassFile(fileName)){
            println('fileName is '+fileName)
            //将要分析的class
            FileInputStream fis = new FileInputStream(file)
            //读取并解析class
            ClassReader classReader = new ClassReader(fis)
            //对class文件写入
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
            //访问class文件相应的内容，解析到某一个结构并通知ClassVisitor的相应方法
            ClassVisitor classVisitor = new MyClassVisitor(classWriter)
            //依次调用classVisitor接口的各个方法
            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
            byte[] bytes = classWriter.toByteArray()
            FileOutputStream outputStream = new FileOutputStream(file.path)
            outputStream.write(bytes)
            outputStream.close()
        }
    }

    /** 检查class文件是否符合条件 */
    def checkClassFile(String name) {
        return name.endsWith("Activity.class");
    }
}
