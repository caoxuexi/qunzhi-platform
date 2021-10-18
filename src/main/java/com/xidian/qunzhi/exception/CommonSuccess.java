package com.xidian.qunzhi.exception;

import com.xidian.qunzhi.exception.http.HttpException;

/**
 * @author Cao Study
 * @description <h1>CommonSuccess</h1>
 * @date 2021-10-18 20:35
 */
public class CommonSuccess extends HttpException {
    public CommonSuccess(int code) {
        this.httpStatusCode = 200;
        this.code = code;
    }
    // 200 201 204 200
    // 200 201 200 200

    // Create：201 资源本身
    // Get: 200
    // Put: 200
    // Delete: 200
}

