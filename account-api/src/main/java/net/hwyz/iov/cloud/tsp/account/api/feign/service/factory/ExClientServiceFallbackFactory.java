package net.hwyz.iov.cloud.tsp.account.api.feign.service.factory;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.account.api.feign.service.ExClientService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 客户端相关服务降级处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
public class ExClientServiceFallbackFactory implements FallbackFactory<ExClientService> {

    @Override
    public ExClientService create(Throwable throwable) {
        return new ExClientService() {
            @Override
            public ClientResponse getMpClient(String accountId) {
                logger.error("客户端服务获取账号最新手机客户端调用失败", throwable);
                return null;
            }
        };
    }
}
