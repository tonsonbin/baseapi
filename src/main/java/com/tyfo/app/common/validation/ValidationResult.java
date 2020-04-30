package com.tyfo.app.common.validation;

import java.util.Map;

/**
 * @Description 请输入该文件注解
 * @Author Benjamin
 * @CreateDate 2018-12-26 13:05
 **/
public class ValidationResult {
    // 校验结果是否有错
    private boolean hasErrors;

    // 校验错误信息
    private Map<String, String> errorMsg;

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }
}
