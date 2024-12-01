package net.hwyz.iov.cloud.tsp.account.service.application.service;

import lombok.RequiredArgsConstructor;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.framework.common.util.ParamHelper;
import net.hwyz.iov.cloud.tsp.account.api.contract.UserIdentity;
import net.hwyz.iov.cloud.tsp.account.service.domain.token.service.TokenService;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.dao.TokenDao;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.TokenPo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 令牌应用服务类
 *
 * @author hwyz_leo
 */
@Service
@RequiredArgsConstructor
public class TokenAppService {

    private final TokenDao tokenDao;
    private final TokenService tokenService;

    /**
     * 查询令牌信息
     *
     * @param accountId   账号ID
     * @param clientId    客户端ID
     * @param clientType  客户端类型
     * @param vin         车架号
     * @param accessToken 访问令牌
     * @param beginTime   开始时间
     * @param endTime     结束时间
     * @return 客户端信息列表
     */
    public List<TokenPo> search(String accountId, String clientId, String clientType, String vin, String accessToken,
                                Date beginTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("accountId", accountId);
        map.put("clientId", clientId);
        map.put("clientType", clientType);
        map.put("vin", ParamHelper.fuzzyQueryParam(vin));
        map.put("accessToken", accessToken);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return tokenDao.selectPoByMap(map);
    }

    /**
     * 令牌身份认证
     *
     * @param token    令牌
     * @param clientId 客户端ID
     * @return 用户身份
     */
    public UserIdentity authenticateMp(String token, String clientId) {
        return tokenService.validateToken(token, ClientType.MP, clientId)
                .map(tokenDo -> UserIdentity.builder()
                        .accountId(tokenDo.getAccountId())
                        .build())
                .orElseThrow(null);
    }

}
