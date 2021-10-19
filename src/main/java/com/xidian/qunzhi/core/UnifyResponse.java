package com.xidian.qunzhi.core;

import com.xidian.qunzhi.exception.CommonSuccess;
import com.xidian.qunzhi.exception.CreateSuccess;
import com.xidian.qunzhi.exception.DeleteSuccess;
import com.xidian.qunzhi.exception.UpdateSuccess;
import lombok.Data;

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

    public static void commonSuccess() {
        throw new CommonSuccess(0);
    }

    public static void createSuccess() {
        throw new CreateSuccess(1);
    }

    public static void updateSuccess() { throw new UpdateSuccess(2); }

    public static void deleteSuccess() {
        throw new DeleteSuccess(3);
    }


}
