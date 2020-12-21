package com.shop.manage.system.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mr.joey
 */
public class ServerResponse<T> implements Serializable {

    /**
     * 错误编码定义
     */
    public static final int DEFAULT_SUCCESS_CODE = 200;
    public static final int DEFAULT_ERROR_CODE = 500;
    public static final int OUTTER_SYSTEM_ERROR_CODE = 501;
    public static final int UNKNOWN_ERROR_CODE = 998;
    public static final int NO_LOGIN_ERROR_CODE = 999;
    /**
     * 节点的名称
     */
    private static Map<Integer, String> STATUS_CODE_MAP = new HashMap<>();

    static {
        STATUS_CODE_MAP.put(DEFAULT_SUCCESS_CODE, "请求成功");
        STATUS_CODE_MAP.put(DEFAULT_ERROR_CODE, "内部业务逻辑处理异常");
        STATUS_CODE_MAP.put(OUTTER_SYSTEM_ERROR_CODE, "调用外部接口异常");
        STATUS_CODE_MAP.put(UNKNOWN_ERROR_CODE, "未知异常");
        STATUS_CODE_MAP.put(NO_LOGIN_ERROR_CODE, "用户未登录");
    }

    /**
     * 微服务各模块间调用时，需要无参构造函数才能反序列化
     * 平时不要使用
     */
    public ServerResponse() {
        this.meta = new Meta();
        this.data = null;
    }

    /**
     * 响应头
     */
    private Meta meta;

    /**
     * 响应data节点
     */
    private T data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 私有构造器
     *
     * @param success
     */
    private ServerResponse(boolean success) {
        this.meta = new Meta(success);
    }

    /**
     * 私有构造器
     *
     * @param success
     * @param message
     */
    private ServerResponse(boolean success, String message) {
        this.meta = new Meta(success, message);
    }

    /**
     * 私有构造器
     *
     * @param success
     * @param message
     * @param statusCode
     */
    private ServerResponse(boolean success, String message, int statusCode) {
        this.meta = new Meta(success, message, statusCode);
    }

    /**
     * 私有构造器
     *
     * @param success
     * @param message
     * @param data
     */
    private ServerResponse(boolean success, String message, T data) {
        this.meta = new Meta(success, message);
        this.data = data;
    }

    /**
     * 私有构造器
     *
     * @param success
     * @param message
     * @param statusCode
     * @param data
     */
    private ServerResponse(boolean success, String message, int statusCode, T data) {
        this.meta = new Meta(success, message, statusCode);
        this.data = data;
    }

    /**
     * 请求成功时的响应方法，其它采用默认信息
     *
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(true);
    }

    /**
     * 请求成功时的响应方法，并自定义成功信息
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(String message) {

        return new ServerResponse<T>(true, message);
    }

    /**
     * 请求成功时的响应方法，并进行指标计数，自定义成功信息和返回数据
     *
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(String message, T data) {
        return new ServerResponse<T>(true, message, data);
    }

    /**
     * 请求失败时的响应方法，采用默认信息
     *
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByFailure() {
        return new ServerResponse<T>(false);
    }

    /**
     * 请求失败时的响应方法，其它采用默认信息
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByFailure(String message) {
        return new ServerResponse<T>(false, message);
    }

    /**
     * 请求失败时的响应方法，自定义响应内容和编码
     *
     * @param message
     * @param statusCode
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByFailure(String message, int statusCode) {
        return new ServerResponse<T>(false, message, statusCode);
    }

    /**
     * 请求异常时的响应方法，其它采用默认信息
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByError(String message) {
        return new ServerResponse<T>(false, message);
    }


    /**
     * 请求异常时的响应方法，自定义响应内容和编码，并进行错误计数，其它采用默认信息
     *
     * @param message
     * @param statusCode
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByError(String message, int statusCode) {
        return new ServerResponse<T>(false, message, statusCode);
    }


    /**
     * 内部类，用于响应头
     */
    public class Meta {

        private Boolean success;

        private String message;

        private Integer statusCode;

        /**
         * 微服务各模块间调用时，需要无参构造函数才能反序列化
         * 平时不要使用
         */
        public Meta() {
            this.success = false;
            this.message = null;
            this.statusCode = DEFAULT_ERROR_CODE;
        }

        public Meta(boolean success) {
            this.success = success;
            this.message = this.success ? STATUS_CODE_MAP.get(DEFAULT_SUCCESS_CODE) : STATUS_CODE_MAP.get(DEFAULT_ERROR_CODE);
            this.statusCode = this.success ? DEFAULT_SUCCESS_CODE : DEFAULT_ERROR_CODE;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
            this.statusCode = this.success ? DEFAULT_SUCCESS_CODE : DEFAULT_ERROR_CODE;
        }

        public Meta(boolean success, String message, int statusCode) {
            this.success = success;
            this.message = message;
            this.statusCode = statusCode;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }
    }

}
