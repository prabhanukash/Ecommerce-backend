package com.flipkart.ecommerce_backend.Exception;

import com.flipkart.ecommerce_backend.hander.ErrorCode;
import lombok.Getter;

/**
 * Thrown when attempting to use an unverified user account
 */
@Getter
public class UserNotVerifiedException extends UserException {
    public UserNotVerifiedException(String email) {
        super(ErrorCode.USER_NOT_VERIFIED, String.format(ErrorCode.USER_NOT_VERIFIED.getDefaultMessage(), email));
    }
}
