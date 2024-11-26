package net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception;


import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.CountryRegion;

/**
 * 手机号码无效异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class MobileInvalidException extends AccountBaseException {

    private static final int ERROR_CODE = 201002;

    public MobileInvalidException(String mobile, CountryRegion countryRegion) {
        super(ERROR_CODE);
        logger.warn("手机号码[{}]无效[{}]", mobile, countryRegion.name);
    }
}
