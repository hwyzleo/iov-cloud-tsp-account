package net.hwyz.iov.cloud.tsp.account.service.domain.client.repository;


import net.hwyz.iov.cloud.framework.common.domain.BaseRepository;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.model.ClientDo;

import java.util.Optional;

/**
 * 客户端领域仓库接口
 *
 * @author hwyz_leo
 */
public interface ClientRepository extends BaseRepository<Long, ClientDo> {

    /**
     * 获取最新的客户端
     *
     * @param clientType 客户端类型
     * @param clientId   客户端ID
     * @return 客户端领域对象
     */
    Optional<ClientDo> getLastClient(ClientType clientType, String clientId);

    /**
     * 获取用户最新的客户端
     *
     * @param clientType 客户端类型
     * @param accountId  账号ID
     * @return 客户端领域对象
     */
    Optional<ClientDo> getAccountLastClient(ClientType clientType, String accountId);

}
