package net.hwyz.iov.cloud.tsp.account.service.application.service;

import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.UpdateClientConfigRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.model.ClientDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.repository.ClientRepository;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.service.ClientService;
import net.hwyz.iov.cloud.tsp.account.service.facade.assembler.ClientResponseAssembler;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.dao.ClientDao;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.ClientPo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户端相关应用服务类
 *
 * @author hwyz_leo
 */
@Service
@RequiredArgsConstructor
public class ClientAppService {

    private final ClientDao clientDao;
    private final ClientService clientService;
    private final ClientRepository clientRepository;

    /**
     * 查询客户端信息
     *
     * @param accountId  账号ID
     * @param clientId   客户端ID
     * @param clientType 客户端类型
     * @param beginTime  开始时间
     * @param endTime    结束时间
     * @return 客户端信息列表
     */
    public List<ClientPo> search(String accountId, String clientId, String clientType, Date beginTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("accountId", accountId);
        map.put("clientId", clientId);
        map.put("clientType", clientType);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return clientDao.selectPoByMap(map);
    }

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
