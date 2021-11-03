package com.xidian.qunzhi.core;

import com.xidian.qunzhi.exception.CommonSuccess;
import com.xidian.qunzhi.exception.CreateSuccess;
import com.xidian.qunzhi.exception.DeleteSuccess;
import com.xidian.qunzhi.exception.UpdateSuccess;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 曹学习
 * @description UnifyResponse
 * @date 2020/8/16 22:54
 */
@Data
public class UnifyResponse {
    private int code;
    private String message;
    private String request;

    public UnifyResponse(int code, String message, String request) {
        this.code = code;
        this.message = message;
        this.request = request;
    }

    public static UnifyResponse commonSuccess(HttpServletRequest request) {
        return new UnifyResponse(0,"ok",request.getMethod()+" "+request.getRequestURI());
    }

    public static UnifyResponse createSuccess(HttpServletRequest request) {
        return new UnifyResponse(1,"创建成功", request.getMethod()+" "+request.getRequestURI());
    }


    public static UnifyResponse createSuccess(HttpServletRequest request,String message) {
        return new UnifyResponse(1,message, request.getMethod()+" "+request.getRequestURI());
    }

    public static UnifyResponse updateSuccess(HttpServletRequest request) {
        return new UnifyResponse(2,"更新成功", request.getMethod()+" "+request.getRequestURI());
    }

    public static UnifyResponse deleteSuccess(HttpServletRequest request) {
        return new UnifyResponse(3,"删除成功", request.getMethod()+" "+request.getRequestURI());
    }


}
