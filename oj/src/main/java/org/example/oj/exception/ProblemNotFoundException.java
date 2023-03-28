package org.example.oj.exception;

/**
 * 题目未找到异常
 */
public class ProblemNotFoundException extends ApplicationException {


    private static final long serialVersionUID = 3919904865407492637L;

    public ProblemNotFoundException(int status, String msg) {
        super(status,msg);
    }
}
