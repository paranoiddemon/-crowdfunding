package cc.landfill.crowd.exception;

/**
 * @title: LoginAccttAlreadyInUserException 新增或者更新admin  账号重复
 * @Author Landfill
 * @Date: 2020/7/26 10:16
 * @Version 1.0
 */
public class LoginAcctAlreadyInUserForUpateException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public LoginAcctAlreadyInUserForUpateException() {
        super();
    }

    public LoginAcctAlreadyInUserForUpateException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUserForUpateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUserForUpateException(Throwable cause) {
        super(cause);
    }

    protected LoginAcctAlreadyInUserForUpateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}