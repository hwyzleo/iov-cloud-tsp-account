package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 账号被禁用异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class AccountNotEnableException extends AccountBaseException {

    public AccountNotEnableException(String accountId) {
        super(ERROR_CODE_ACCOUNT_NOT_ENABLE);
        logger.warn("账号[{}]已被禁用", accountId);
    }
}
