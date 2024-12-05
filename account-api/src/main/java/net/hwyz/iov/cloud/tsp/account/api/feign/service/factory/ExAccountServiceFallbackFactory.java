package net.hwyz.iov.cloud.tsp.account.api.feign.service.factory;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.Account;
import net.hwyz.iov.cloud.tsp.account.api.feign.service.ExAccountService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 账号相关服务降级处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
public class ExAccountServiceFallbackFactory implements FallbackFactory<ExAccountService> {

    @Override
    public ExAccountService create(Throwable throwable) {
        return new ExAccountService() {
            @Override
            public Account getAccountInfo(String accountId) {
                logger.error("账号服务获取账号信息调用失败", throwable);
                return null;
            }
        };
    }
}
