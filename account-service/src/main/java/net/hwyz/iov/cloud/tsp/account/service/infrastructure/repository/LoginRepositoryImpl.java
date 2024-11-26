package net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.domain.AbstractRepository;
import net.hwyz.iov.cloud.framework.common.enums.CountryRegion;
import net.hwyz.iov.cloud.tsp.account.service.domain.login.model.LoginDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.login.repository.LoginRepository;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.cache.CacheService;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.assembler.LoginPoAssembler;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static net.hwyz.iov.cloud.framework.common.domain.DoState.CHANGED;
import static net.hwyz.iov.cloud.framework.common.domain.DoState.NEW;

/**
 * 登录领域仓库接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl extends AbstractRepository<Long, LoginDo> implements LoginRepository {

    final CacheService cacheService;

    @Override
    public Optional<LoginDo> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean save(LoginDo loginDo) {
        if (logger.isDebugEnabled()) {
            logger.debug("保存登录领域对象[{}]", JSONUtil.parse(loginDo).toJSONString(0));
        }
        switch (loginDo.getState()) {
            case NEW, CHANGED -> cacheService.setMobileLogin(LoginPoAssembler.INSTANCE.fromDo(loginDo));
            default -> {
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<LoginDo> getByMobile(CountryRegion countryRegion, String mobile) {
        logger.debug("根据手机号[{}:{}]获取登录领域对象", countryRegion.code, mobile);
        return cacheService.getMobileLogin(countryRegion, mobile).map(loginPo -> {
            LoginDo loginDo = LoginPoAssembler.INSTANCE.toDo(loginPo);
            loginDo.stateLoad();
            return loginDo;
        });
    }
}
