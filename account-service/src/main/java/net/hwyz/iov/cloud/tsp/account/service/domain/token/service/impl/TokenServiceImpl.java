package net.hwyz.iov.cloud.tsp.account.service.domain.token.service.impl;

import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.account.service.domain.factory.TokenFactory;
import net.hwyz.iov.cloud.tsp.account.service.domain.token.model.TokenDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.token.repository.TokenRepository;
import net.hwyz.iov.cloud.tsp.account.service.domain.token.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 令牌领域服务接口实现类
 *
 * @author hwyz_leo
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    final TokenFactory factory;
    final TokenRepository repository;

    @Override
    public TokenDo createMpToken(String accountId, String clientId) {
        TokenDo tokenDo = factory.buildMobile(accountId, clientId);
        tokenDo.init();
        repository.save(tokenDo);
        return tokenDo;
    }

    @Override
    public Optional<TokenDo> validateToken(String token, ClientType clientType, String clientId) {
        return repository.getToken(token, clientType, clientId);
    }
}
