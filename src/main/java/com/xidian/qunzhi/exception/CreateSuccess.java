package com.xidian.qunzhi.exception;


import com.xidian.qunzhi.exception.http.HttpException;

/**
 * @author 曹学习
 * @description ServerErrorException
 * @date 2020/8/23 23:36
 */
public class CreateSuccess extends HttpException {
    public CreateSuccess(int code) {
        this.httpStatusCode = 201;
        this.code = code;
    }
    // 200 201 204 200
    // 200 201 200 200

    // Create：201 资源本身
    // Get: 200
    // Put: 200
    // Delete: 200
}
