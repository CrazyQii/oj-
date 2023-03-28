package org.example.oj.exception;

/**
 * 用户认证失败异常
 */
public class UserUnAuthorizedException extends ApplicationException{

    private static final long serialVersionUID = 2975352570900978436L;

    public UserUnAuthorizedException(int status, String msg) {
        super(status, msg);
    }
}
