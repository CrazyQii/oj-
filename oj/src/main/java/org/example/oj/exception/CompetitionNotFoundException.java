package org.example.oj.exception;

/**
 * 比赛模块异常
 */
public class CompetitionNotFoundException extends ApplicationException{


    private static final long serialVersionUID = -997462919114053391L;

    public CompetitionNotFoundException(int status, String msg) {
        super(status, msg);
    }
}
