package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;

import net.hwyz.iov.cloud.tsp.framework.commons.exception.BaseException;

/**
 * 账号服务基础异常
 *
 * @author hwyz_leo
 */
public class AccountBaseException extends BaseException {

    private static final int ERROR_CODE = 201000;

    public AccountBaseException(String message) {
        super(ERROR_CODE, message);
    }

    public AccountBaseException(int errorCode, String message) {
        super(errorCode, message);
    }

}
