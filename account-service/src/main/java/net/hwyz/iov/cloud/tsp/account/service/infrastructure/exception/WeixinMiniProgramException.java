package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信小程序异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class WeixinMiniProgramException extends AccountBaseException {

    private static final int ERROR_CODE = 201005;

    public WeixinMiniProgramException(String message) {
        super(ERROR_CODE);
        logger.warn("微信小程序异常[{}]", message);
    }
}
