package org.example.oj.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.oj.common.ResponseEnum;

import java.io.Serializable;

/**
 * 保证序列化json的时候null值不序列化
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RestResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -7126786145980795737L;

    private Integer status;

    private String msg;

    private T data;

    private RestResponseVO() {
    }

    private RestResponseVO(int status) {
        this.status = status;
    }

    private RestResponseVO(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private RestResponseVO(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private RestResponseVO(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;

    }


    public static <T> RestResponseVO<T> createBySuccess() {
        return new RestResponseVO<>(ResponseEnum.SUCCESS.getStatus());
    }

    public static <T> RestResponseVO<T> createBySuccessMessage(String msg) {
        return new RestResponseVO<>(ResponseEnum.SUCCESS.getStatus(), msg);
    }

    public static <T> RestResponseVO<T> createBySuccess(T data) {
        return new RestResponseVO<>(ResponseEnum.SUCCESS.getStatus(), data);
    }

    public static <T> RestResponseVO<T> createBySuccessMessage(String msg, T data) {
        return new RestResponseVO<>(ResponseEnum.SUCCESS.getStatus(), msg, data);
    }


    public static <T> RestResponseVO<T> createByError() {
        return new RestResponseVO<>(ResponseEnum.ERROR.getStatus(), ResponseEnum.ERROR.getDesc());
    }

    public static <T> RestResponseVO<T> createByErrorEnum(ResponseEnum responseEnum) {
        return new RestResponseVO<>(responseEnum.getStatus(), responseEnum.getDesc());
    }


    public static <T> RestResponseVO<T> createByErrorMessage(String msg) {
        return new RestResponseVO<>(ResponseEnum.ERROR.getStatus(), msg);
    }

    public static <T> RestResponseVO<T> createByErrorStatusMessage(int errorStatus, String msg) {
        return new RestResponseVO<>(errorStatus, msg);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status.equals(ResponseEnum.SUCCESS.getStatus()) ;
    }


    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "RestResponseVO{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
