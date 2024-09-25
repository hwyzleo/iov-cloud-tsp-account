package net.hwyz.iov.cloud.tsp.account.service.domain.account.repository;

import net.hwyz.iov.cloud.tsp.account.service.domain.account.model.AccountDo;
import net.hwyz.iov.cloud.tsp.framework.commons.domain.BaseRepository;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.CountryRegion;

import java.util.Optional;

/**
 * 账号领域仓库接口
 *
 * @author hwyz_leo
 */
public interface AccountRepository extends BaseRepository<Long, AccountDo> {

    /**
     * 根据手机号获取领域对象
     *
     * @param countryRegion 国家或地区
     * @param mobile        手机号
     * @return 领域对象
     */
    Optional<AccountDo> getByMobile(CountryRegion countryRegion, String mobile);

    /**
     * 根据UID获取领域对象
     *
     * @param accountId 账号唯一ID
     * @return 领域对象
     */
    Optional<AccountDo> getByAccountId(String accountId);

}
