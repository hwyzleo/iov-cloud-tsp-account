package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 手机发送登录验证码锁定异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class MobileLoginSendLockException extends AccountBaseException {

    private static final int ERROR_CODE = 201003;

    public MobileLoginSendLockException(String mobile) {
        super(ERROR_CODE);
        logger.warn("手机[{}]发送登录验证码已锁定", mobile);
    }
}
