package com.du.block.exception;

import com.du.block.constant.CommonConstant;
import com.du.block.constant.ErrorCode;
import org.apache.logging.log4j.util.Strings;

/**
 * SDK异常类
 * @author darwindu
 * @date 2019/3/4
 **/
public class SdkException extends RuntimeException {

    private Integer errorCode;
    private String errorMessage;

    public SdkException(Throwable cause) {
        super(cause);
    }

    public SdkException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public SdkException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * constructor.
     * @param cause exception object
     * @param errorCode exception error code.
     * @param errorMessage exception error messave.
     */
    public SdkException(Integer errorCode, String errorMessage, Throwable cause) {
        super(getMsg(errorCode, errorMessage), cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * constructor.
     * @param errorCode exception enum errorcode
     * @param cause exception object
     */
    public SdkException(ErrorCode errorCode, Throwable cause) {
        super(getMsg(errorCode), cause);
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getCodeDesc();
    }

    /**
     * constructor.
     * @param errorCode exception error code
     * @param errorMessage exception error messave
     */
    public SdkException(Integer errorCode, String errorMessage) {
        super(getMsg(errorCode, errorMessage));
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * constructor.
     * @param errorCode exception enum errorcode
     */
    public SdkException(ErrorCode errorCode) {
        super(getMsg(errorCode));
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getCodeDesc();
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private static String getMsg(ErrorCode errorCode) {
        return getMsg(errorCode.getCode(), errorCode.getCodeDesc());
    }

    private static String getMsg(Integer errorCode, String errorMessage) {

        return new StringBuffer(errorCode == null ? Strings.EMPTY : errorCode.toString())
            .append(CommonConstant.SYMBOL_VERTICAL)
            .append(errorMessage == null ? Strings.EMPTY : errorMessage).toString();
    }
}
