package net.hwyz.iov.cloud.tsp.account.api.feign.service.factory;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.UserIdentity;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.AuthenticationRequest;
import net.hwyz.iov.cloud.tsp.account.api.feign.service.ExTokenService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 令牌相关服务降级处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
public class ExTokenServiceFallbackFactory implements FallbackFactory<ExTokenService> {

    @Override
    public ExTokenService create(Throwable throwable) {
        return new ExTokenService() {
            @Override
            public UserIdentity authenticateMp(AuthenticationRequest authenticationRequest) {
                logger.error("令牌服务手机令牌身份认证调用失败", throwable);
                return null;
            }
        };
    }
}
