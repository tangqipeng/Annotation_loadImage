package com.example.loadimage;

/**
 * @author tangqipeng
 * @date 2020/9/23 3:04 PM
 * @email tangqipeng@aograph.com
 */
public class RoleAnnoTest {
    @Roles(roles = {@Role(value = "role1"), @Role(value = "role2")})
    public String doString(){
        return "";
    }
}
