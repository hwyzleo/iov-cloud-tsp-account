package net.hwyz.iov.cloud.tsp.account.service.domain.factory;

import net.hwyz.iov.cloud.tsp.account.service.domain.token.model.TokenDo;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.ClientType;
import org.springframework.stereotype.Component;

/**
 * 令牌领域工厂类
 *
 * @author hwyz_leo
 */
@Component
public class TokenFactory {

    /**
     * 创建手机令牌
     *
     * @param accountId      账号唯一ID
     * @param clientId 客户端ID
     * @return 令牌领域对象
     */
    public TokenDo buildMobile(String accountId, String clientId) {
        return TokenDo.builder()
                .accountId(accountId)
                .clientId(clientId)
                .clientType(ClientType.MP)
                .build();
    }

}
