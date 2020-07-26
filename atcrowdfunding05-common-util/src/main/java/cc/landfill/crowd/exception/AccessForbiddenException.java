package cc.landfill.crowd.exception;

/**
 * @title: AccessForbiddenException
 * @Author Landfill
 * @Date: 2020/7/25 10:54
 * @Version 1.0
 */
public class AccessForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}