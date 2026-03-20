package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;


import net.hwyz.iov.cloud.framework.common.exception.BaseException;

/**
 * 账号服务基础异常
 *
 * @author hwyz_leo
 */
public class AccountBaseException extends BaseException {

    private static final int ERROR_CODE = 201000;
    protected static final int ERROR_CODE_MOBILE_LOGIN_VERIFY_CODE_INCORRECT = 201001;
    protected static final int ERROR_CODE_MOBILE_INVALID = 201002;
    protected static final int ERROR_CODE_MOBILE_LOGIN_SEND_LOCK = 201003;
    protected static final int ERROR_CODE_ACCOUNT_NOT_EXIST = 201004;
    protected static final int ERROR_CODE_WEIXIN_MINI_PROGRAM_EXCEPTION = 201005;
    protected static final int ERROR_CODE_ACCOUNT_NOT_ENABLE = 201006;

    public AccountBaseException(String message) {
        super(ERROR_CODE, message);
    }

    public AccountBaseException(int errorCode) {
        super(errorCode);
    }

    public AccountBaseException(int errorCode, String message) {
        super(errorCode, message);
    }

}
