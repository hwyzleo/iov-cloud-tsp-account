package net.hwyz.iov.cloud.tsp.account.service.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.account.service.application.service.ClientAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端相关服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/service/client")
public class ClientServiceController {

    private final ClientAppService clientAppService;

    /**
     * 获取账号最新手机客户端
     *
     * @param accountId 账号ID
     * @return 客户端信息
     */
    @GetMapping(value = "/mp")
    public ClientResponse getMpClient(@RequestParam String accountId) {
        logger.info("获取账号[{}]最新手机客户端", accountId);
        return clientAppService.getMpClient(accountId);
    }
}
