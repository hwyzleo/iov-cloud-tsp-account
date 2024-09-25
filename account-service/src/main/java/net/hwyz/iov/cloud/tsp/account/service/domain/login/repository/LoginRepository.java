package net.hwyz.iov.cloud.tsp.account.service.domain.login.repository;


import net.hwyz.iov.cloud.tsp.account.service.domain.login.model.LoginDo;
import net.hwyz.iov.cloud.tsp.framework.commons.domain.BaseRepository;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.CountryRegion;

import java.util.Optional;

/**
 * 登录领域仓库接口
 *
 * @author hwyz_leo
 */
public interface LoginRepository extends BaseRepository<Long, LoginDo> {

    /**
     * 根据国家与地区代码与手机号获取领域对象
     *
     * @param countryRegion 国家与地区
     * @param mobile        手机号
     * @return 领域对象
     */
    Optional<LoginDo> getByMobile(CountryRegion countryRegion, String mobile);

}
