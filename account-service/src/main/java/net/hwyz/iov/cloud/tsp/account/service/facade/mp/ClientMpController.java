package net.hwyz.iov.cloud.tsp.account.service.facade.mp;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.UpdateClientConfigRequest;
import net.hwyz.iov.cloud.tsp.account.api.feign.mp.ClientMpApi;
import net.hwyz.iov.cloud.tsp.account.service.application.service.ClientAppService;
import net.hwyz.iov.cloud.tsp.framework.commons.bean.Response;
import org.springframework.web.bind.annotation.*;

/**
 * 客户端相关手机接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mp/client")
public class ClientMpController implements ClientMpApi {

    private final ClientAppService clientAppService;

    /**
     * 更新客户端配置
     *
     * @param clientId 客户端ID
     * @param request  更新客户端配置请求
     * @return 请求结果
     */
    @Override
    @PostMapping("/action/updateConfig")
    public Response<Void> updateClientConfig(@RequestHeader String clientId, @RequestBody @Valid UpdateClientConfigRequest request) {
        logger.info("手机客户端[{}]更新客户端配置", clientId);
        clientAppService.updateMpConfig(clientId, request);
        return new Response<>();
    }

}
