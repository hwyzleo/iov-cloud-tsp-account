package net.hwyz.iov.cloud.tsp.account.api.feign.service;


import jakarta.validation.Valid;
import net.hwyz.iov.cloud.framework.common.constant.ServiceNameConstants;
import net.hwyz.iov.cloud.tsp.account.api.contract.UserIdentity;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.AuthenticationRequest;
import net.hwyz.iov.cloud.tsp.account.api.feign.service.factory.ExTokenServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 令牌相关服务接口
 *
 * @author hwyz_leo
 */
@FeignClient(contextId = "exTokenService", value = ServiceNameConstants.TSP_ACCOUNT, path = "/service/token", fallbackFactory = ExTokenServiceFallbackFactory.class)
public interface ExTokenService {

    /**
     * 手机令牌身份认证
     *
     * @param authenticationRequest 身份认证请求
     * @return 用户身份
     */
    @PostMapping(value = "/authenticateMp")
    UserIdentity authenticateMp(@RequestBody @Valid AuthenticationRequest authenticationRequest);

}
