package com.flipkart.ecommerce_backend.Exception;

import com.flipkart.ecommerce_backend.hander.ErrorCode;

import java.util.Collections;
import java.util.List;

public abstract class BusinessException extends BaseException {
    private final List<String> details;

    protected BusinessException(ErrorCode errorCode, String message, List<String> details) {
        super(errorCode, message);
        this.details = details;
    }

    protected BusinessException(ErrorCode errorCode, String message) {
        this(errorCode, message, Collections.emptyList());
    }

}
