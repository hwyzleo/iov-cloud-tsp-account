package net.hwyz.iov.cloud.tsp.account.service.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.Account;
import net.hwyz.iov.cloud.tsp.account.service.application.service.AccountAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号相关服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/service/account")
public class AccountServiceController {

    private final AccountAppService accountAppService;

    /**
     * 获取账号信息
     *
     * @param accountId 账号ID
     * @return 账号信息
     */
    @GetMapping(value = "/{accountId}")
    public Account getAccountInfo(@PathVariable("accountId") String accountId) {
        logger.info("获取账号[{}]信息", accountId);
        return accountAppService.getAccountInfo(accountId);
    }

}
