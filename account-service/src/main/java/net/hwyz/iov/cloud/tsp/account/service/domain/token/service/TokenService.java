package net.hwyz.iov.cloud.tsp.account.service.domain.token.service;


import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.account.service.domain.token.model.TokenDo;

import java.util.Optional;

/**
 * 令牌领域服务接口
 *
 * @author hwyz_leo
 */
public interface TokenService {

    /**
     * 创建手机端令牌
     *
     * @param accountId 用户唯一ID
     * @param clientId  客户端ID
     * @return 令牌领域对象
     */
    TokenDo createMpToken(String accountId, String clientId);

    /**
     * 验证令牌
     *
     * @param token      令牌
     * @param clientType 客户端类型
     * @param clientId   客户端ID
     * @return 令牌领域对象
     */
    Optional<TokenDo> validateToken(String token, ClientType clientType, String clientId);

}
