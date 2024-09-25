package net.hwyz.iov.cloud.tsp.account.service.infrastructure.cache.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.cache.CacheService;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.AccountPo;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.LoginPo;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.TokenPo;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.ClientType;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.CountryRegion;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 缓存服务实现类
 *
 * @author hwyz_leo
 */
@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    final RedisTemplate<String, String> redisTemplate;

    /**
     * Redis Key前缀：登录
     */
    private static final String REDIS_KEY_PREFIX_LOGIN = "account:login:";
    /**
     * Redis Key前缀：令牌
     */
    private static final String REDIS_KEY_PREFIX_TOKEN = "account:token:";
    /**
     * Redis Key前缀：账号
     */
    private static final String REDIS_KEY_PREFIX_ACCOUNT = "account:acc:";

    @Override
    public Optional<LoginPo> getMobileLogin(CountryRegion countryRegion, String mobile) {
        String loginDoJson = redisTemplate.opsForValue().get(REDIS_KEY_PREFIX_LOGIN + countryRegion.code + "-" + mobile);
        if (StrUtil.isNotBlank(loginDoJson)) {
            return Optional.of(JSONUtil.toBean(loginDoJson, LoginPo.class));
        }
        return Optional.empty();
    }

    @Override
    public void setMobileLogin(LoginPo loginPo) {
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX_LOGIN + loginPo.getCountryRegionCode() + "-" + loginPo.getMobile(),
                JSONUtil.parse(loginPo).toJSONString(0));
    }

    @Override
    public Optional<TokenPo> getToken(ClientType clientType, String token) {
        String tokenJson = redisTemplate.opsForValue().get(REDIS_KEY_PREFIX_TOKEN + clientType.name() + ":" + token);
        if (StrUtil.isNotBlank(tokenJson)) {
            return Optional.of(JSONUtil.toBean(tokenJson, TokenPo.class));
        }
        return Optional.empty();
    }

    @Override
    public void setToken(TokenPo tokenPo) {
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX_TOKEN + tokenPo.getClientType() + ":" + tokenPo.getAccessToken(),
                JSONUtil.parse(tokenPo).toJSONString(0));
    }

    @Override
    public Optional<AccountPo> getAccount(String accountId) {
        String accountJson = redisTemplate.opsForValue().get(REDIS_KEY_PREFIX_ACCOUNT + accountId);
        if (StrUtil.isNotBlank(accountJson)) {
            return Optional.of(JSONUtil.toBean(accountJson, AccountPo.class));
        }
        return Optional.empty();
    }

    @Override
    public void setAccount(AccountPo accountPo) {
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX_ACCOUNT + accountPo.getAccountId(), JSONUtil.parse(accountPo).toJSONString(0));
    }
}
