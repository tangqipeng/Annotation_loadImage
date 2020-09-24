#项目说明
##编写粗糙的图片框架
-利用的是HttpURLConnection，后面会优化

## 使用Android的Paint画出不同的图形，加深对canvas和path的理解
-画了个篮球

## 使用属性动画，熟悉动画效果
- 点击篮球可以运动

## 了解元注解的各个注解的使用
- 利用@Documented生成了doc文件夹中的文件，使用浏览器打开index.html就有相关说明

## 使用APT注解处理器
- 根据网上的提供注解处理器的方法完成注解处理器，但是遇到一个问题，就是项目的gradle升级后注解处理器不生效了，生成不了factory类
- 经过多次尝试，根据Android studio的提示，将factorylib这个java-library的module下的build.gradle中的'com.google.auto.service:auto-service:1.0-rc4'前的引用变为annotationProcessor
- 但是这样一来发现代码中找不到AutoService注解类了，于是我又加上了一个implementation 'com.google.auto.service:auto-service:1.0-rc4'，误打误撞就这么好了
- 生成的factory类在app/build/intermediates/javac这个文件夹下面

## 用gradle插件实现ASM插桩
- 使用groovy实现gradle插件
