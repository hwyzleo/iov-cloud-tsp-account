package net.hwyz.iov.cloud.tsp.account.api.feign.service;

import net.hwyz.iov.cloud.framework.common.constant.ServiceNameConstants;
import net.hwyz.iov.cloud.tsp.account.api.contract.Account;
import net.hwyz.iov.cloud.tsp.account.api.feign.service.factory.ExAccountServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 账号相关服务接口
 *
 * @author hwyz_leo
 */
@FeignClient(contextId = "exAccountService", value = ServiceNameConstants.TSP_ACCOUNT, path = "/service/account", fallbackFactory = ExAccountServiceFallbackFactory.class)
public interface ExAccountService {

    /**
     * 获取账号信息
     *
     * @param accountId 账号ID
     * @return 账号信息
     */
    @GetMapping(value = "/{accountId}")
    Account getAccountInfo(@PathVariable String accountId);

}
