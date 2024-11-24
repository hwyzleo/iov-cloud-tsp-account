package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 账号被禁用异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class AccountNotEnableException extends AccountBaseException {

    private static final int ERROR_CODE = 201006;

    public AccountNotEnableException(String accountId) {
        super(ERROR_CODE);
        logger.warn("账号[{}]已被禁用", accountId);
    }
}
