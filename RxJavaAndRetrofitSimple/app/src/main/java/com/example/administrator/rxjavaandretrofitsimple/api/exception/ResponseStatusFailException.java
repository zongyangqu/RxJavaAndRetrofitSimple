package com.example.administrator.rxjavaandretrofitsimple.api.exception;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/22
 *
 * 类描述：
 */

public class ResponseStatusFailException extends Exception {

    public ResponseStatusFailException() {
    }

    public ResponseStatusFailException(String detailMessage) {
        super(detailMessage);
    }

    public ResponseStatusFailException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ResponseStatusFailException(Throwable throwable) {
        super(throwable);
    }
}

