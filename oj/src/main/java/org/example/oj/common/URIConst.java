package org.example.oj.common;

import java.util.Arrays;

/**
 * 网页路由常量定义
 */
public final class URIConst {

    public static final String[] VALIDATE_CODE_ARRAY_URI = new String[]{
            "/user/sendRegisterEmail", "/user/loginProcess",
            "/user/sendForgetEmail","/backend/user/loginProcess"
    };

    // 权限限制
    public static final String[] NOT_ALLOWED_URI = new String[]{
            "/backend/**"
    };

    // 无权限限制
    public static final String[] ALLOWED_URI = new String[]{
            "/backend/user/loginPage",
    };
}
