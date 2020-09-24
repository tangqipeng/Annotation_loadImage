package com.tqp.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author tangqipeng* @date 2020/9/24 2:33 PM
 * @email tangqipeng@aograph.com
 */
class ASMPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        println('ASMPlugin')
        AppExtension appExtension = project.getExtensions().getByType(AppExtension)
        appExtension.registerTransform(new MyTransform())
    }
}
