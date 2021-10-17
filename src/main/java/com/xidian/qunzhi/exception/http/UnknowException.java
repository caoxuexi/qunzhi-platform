package com.xidian.qunzhi.exception.http;

/**
 * @author Cao Study
 * @description <h1>UnknowException</h1>
 * @date 2021-10-17 22:16
 */
public class UnknowException extends HttpException{
    public UnknowException(){
        this.code = 999;
        this.httpStatusCode = 500;
    }

}
