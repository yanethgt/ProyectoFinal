package edu.uan.fis.jeesample.actions;

/**
 * This class encapsulates the result of a command execution
 * @author wjforero
 */
public class CommandResult {
    
    private String errorCode = "0";
    private String errorMessage = "";
    private String result = CommandResult.DEFAULT;
    
    public static final String DEFAULT = "home";

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{result:" + result
                + ", errorCode: " + errorCode
                + ", errorMessage: " + errorMessage
                + "}";
    }
    
    
    
}
