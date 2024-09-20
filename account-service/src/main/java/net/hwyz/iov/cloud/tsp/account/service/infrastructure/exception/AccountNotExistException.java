package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 账号不存在异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class AccountNotExistException extends AccountBaseException {

    private static final int ERROR_CODE = 201004;

    public AccountNotExistException(String accountId) {
        super(ERROR_CODE);
        logger.warn("账号[{}]不存在", accountId);
    }
}
