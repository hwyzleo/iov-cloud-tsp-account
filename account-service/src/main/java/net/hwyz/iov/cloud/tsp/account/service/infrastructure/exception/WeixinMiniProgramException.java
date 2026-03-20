package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信小程序异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class WeixinMiniProgramException extends AccountBaseException {

    public WeixinMiniProgramException(String message) {
        super(ERROR_CODE_WEIXIN_MINI_PROGRAM_EXCEPTION);
        logger.warn("微信小程序异常[{}]", message);
    }
}
