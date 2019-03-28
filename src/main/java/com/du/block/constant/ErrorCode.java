package com.du.block.constant;

/**
 * Define Error Code and the corresponding Error Message.
 *
 * @author lingfenghe
 */
public enum ErrorCode {

    /**
     * The success.
     */
    SUCCESS(0, "success"),

    /**
     * other uncatched exceptions or error.
     */
    UNKNOW_ERROR(160101, "unknow error, please check the error log."),

    /**
     * illegal input.
     */
    ILLEAGAL_INPUT(160102, "illegal input"),
    ;

    /**
     * error code.
     */
    private int code;

    /**
     * error message.
     */
    private String codeDesc;

    /**
     * Error Code Constructor.
     *
     * @param code The ErrorCode
     * @param codeDesc The ErrorCode Description
     */
    ErrorCode(int code, String codeDesc) {
        this.code = code;
        this.codeDesc = codeDesc;
    }

    /**
     * Get the Error Code.
     *
     * @return the ErrorCode
     */
    public int getCode() {
        return code;
    }

    /**
     * Set the Error Code.
     *
     * @param code the new ErrorCode
     */
    protected void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets the ErrorCode Description.
     *
     * @return the ErrorCode Description
     */
    public String getCodeDesc() {
        return codeDesc;
    }

    /**
     * Sets the ErrorCode Description.
     *
     * @param codeDesc the new ErrorCode Description
     */
    protected void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
}
