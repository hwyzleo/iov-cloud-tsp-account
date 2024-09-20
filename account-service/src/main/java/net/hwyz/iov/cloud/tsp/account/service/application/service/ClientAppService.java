package net.hwyz.iov.cloud.tsp.account.service.application.service;

import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.UpdateClientConfigRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.model.ClientDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.repository.ClientRepository;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.service.ClientService;
import net.hwyz.iov.cloud.tsp.account.service.facade.assembler.ClientResponseAssembler;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.ClientType;
import org.springframework.stereotype.Service;

/**
 * 客户端相关应用服务类
 *
 * @author hwyz_leo
 */
@Service
@RequiredArgsConstructor
public class ClientAppService {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    /**
     * 获取手机客户端
     *
     * @param accountId 账号ID
     * @return 客户端信息
     */
    public ClientResponse getMpClient(String accountId) {
        return clientRepository.getAccountLastClient(ClientType.MP, accountId)
                .map(ClientResponseAssembler.INSTANCE::fromDo)
                .orElse(null);
    }

    /**
     * 更新手机客户端配置
     *
     * @param clientId 客户端ID
     * @param request  更新客户端配置请求
     */
    public void updateMpConfig(String clientId, UpdateClientConfigRequest request) {
        ClientDo clientDo = clientService.getOrCreate(clientId, ClientType.MP);
        clientDo.updatePushRegId(request.getPushRegId());
        clientDo.updateOs(request.getOs());
        clientRepository.save(clientDo);
    }

}
