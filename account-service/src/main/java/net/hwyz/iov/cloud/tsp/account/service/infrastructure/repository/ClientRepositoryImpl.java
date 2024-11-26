package net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.domain.AbstractRepository;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.model.ClientDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.repository.ClientRepository;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.assembler.ClientPoAssembler;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.dao.ClientDao;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.ClientPo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 客户端领域仓库接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl extends AbstractRepository<Long, ClientDo> implements ClientRepository {

    private final ClientDao clientDao;

    @Override
    public Optional<ClientDo> getLastClient(ClientType clientType, String clientId) {
        ClientPo clientPo = clientDao.selectLastPoByExample(ClientPo.builder()
                .clientType(clientType.name())
                .clientId(clientId)
                .build());
        if (clientPo != null) {
            ClientDo clientDo = ClientPoAssembler.INSTANCE.toDo(clientPo);
            clientDo.stateLoad();
            return Optional.of(clientDo);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClientDo> getAccountLastClient(ClientType clientType, String accountId) {
        ClientPo clientPo = clientDao.selectLastPoByExample(ClientPo.builder()
                .clientType(clientType.name())
                .accountId(accountId)
                .build());
        if (clientPo != null) {
            ClientDo clientDo = ClientPoAssembler.INSTANCE.toDo(clientPo);
            clientDo.stateLoad();
            return Optional.of(clientDo);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClientDo> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean save(ClientDo clientDo) {
        if (logger.isDebugEnabled()) {
            logger.debug("保存客户端领域对象[{}]", JSONUtil.parse(clientDo).toJSONString(0));
        }
        switch (clientDo.getState()) {
            case NEW -> clientDao.insertPo(ClientPoAssembler.INSTANCE.fromDo(clientDo));
            case CHANGED -> clientDao.updatePo(ClientPoAssembler.INSTANCE.fromDo(clientDo));
            default -> {
                return false;
            }
        }
        return true;
    }

}
