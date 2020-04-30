package com.tyfo.app.common.web;

/**
 * 权限异常
 */
public class PermissionException extends RuntimeException {

    public PermissionException() {
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
