package com.example.annotationlib;

/**
 * @author tangqipeng
 * @date 2020/9/23 3:10 PM
 * @email tangqipeng@aograph.com
 */

public class BRoleAnnoTest {
    //需要java8支持，在build.gradle中开启Java8
    @BRole(roleName = "role1")
    @BRole(roleName = "role2")
    public String doString(){
        return "";
    }
}
