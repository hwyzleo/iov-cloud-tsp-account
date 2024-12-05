package net.hwyz.iov.cloud.tsp.account.api.feign.service;

import net.hwyz.iov.cloud.framework.common.constant.ServiceNameConstants;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.account.api.feign.service.factory.ExClientServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 客户端相关服务接口
 *
 * @author hwyz_leo
 */
@FeignClient(contextId = "exClientService", value = ServiceNameConstants.TSP_ACCOUNT, path = "/service/client", fallbackFactory = ExClientServiceFallbackFactory.class)
public interface ExClientService {

    /**
     * 获取账号最新手机客户端
     *
     * @param accountId 账号ID
     * @return 客户端信息
     */
    @GetMapping(value = "/mp")
    ClientResponse getMpClient(@RequestParam String accountId);

}
