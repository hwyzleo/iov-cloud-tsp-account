package net.hwyz.iov.cloud.tsp.account.service.domain.account.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.model.AccountDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.repository.AccountRepository;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.service.AccountService;
import net.hwyz.iov.cloud.tsp.account.service.domain.factory.AccountFactory;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.CountryRegion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 账号领域服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountFactory factory;
    private final AccountRepository repository;

    @Value("${biz.default-avatar}")
    private String defaultAvatar;

    @Override
    public AccountDo getOrCreate(CountryRegion countryRegion, String mobile) {
        logger.info("根据手机号[{}:{}]获取或创建账号", countryRegion.code, mobile);
        return repository.getByMobile(countryRegion, mobile).orElseGet(() -> {
            AccountDo newAccountDo = factory.build(countryRegion, mobile);
            newAccountDo.init();
            newAccountDo.modifyAvatar(defaultAvatar);
            return newAccountDo;
        });
    }

    @Override
    public Optional<AccountDo> get(String accountId) {
        logger.info("根据账号ID[{}]获取账号", accountId);
        return repository.getByAccountId(accountId);
    }
}
