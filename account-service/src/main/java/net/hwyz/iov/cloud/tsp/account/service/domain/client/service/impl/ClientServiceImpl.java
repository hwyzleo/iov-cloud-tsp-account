package net.hwyz.iov.cloud.tsp.account.service.domain.client.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.model.ClientDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.repository.ClientRepository;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.service.ClientService;
import net.hwyz.iov.cloud.tsp.account.service.domain.factory.ClientFactory;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.ClientType;
import org.springframework.stereotype.Service;

/**
 * 客户端领域服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientFactory factory;
    private final ClientRepository repository;

    @Override
    public ClientDo getOrCreate(String clientId, ClientType clientType) {
        return repository.getLastClient(clientType, clientId).orElseGet(() -> {
            ClientDo newClientDo = factory.build(clientId, clientType);
            newClientDo.init();
            return newClientDo;
        });
    }

    @Override
    public ClientDo login(String clientId, ClientType clientType, String accountId) {
        logger.info("客户端[{}:{}]登录用户[{}]", clientType, clientId, accountId);
        ClientDo clientDo = getOrCreate(clientId, clientType);
        clientDo.login(accountId);
        repository.save(clientDo);
        return clientDo;
    }
}
