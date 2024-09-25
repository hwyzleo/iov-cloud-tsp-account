package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;


import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.CountryRegion;

/**
 * 手机登录验证码不正确异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class MobileLoginVerifyCodeIncorrectException extends AccountBaseException {

    private static final int ERROR_CODE = 201001;

    public MobileLoginVerifyCodeIncorrectException(CountryRegion countryRegion, String mobile) {
        super(ERROR_CODE);
        logger.warn("手机[{}:{}]登录验证码验证失败", countryRegion.code, mobile);
    }
}
