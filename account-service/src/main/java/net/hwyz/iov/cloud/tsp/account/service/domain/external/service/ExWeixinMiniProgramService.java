package net.hwyz.iov.cloud.tsp.account.service.domain.external.service;

import net.hwyz.iov.cloud.tsp.account.api.contract.Account;

/**
 * 外部微信小程序服务
 *
 * @author hwyz_leo
 */
public interface ExWeixinMiniProgramService {

    /**
     * 根据手机号授权码获取手机号
     *
     * @param mobileCode 手机号授权码
     * @return 账号手机信息
     */
    Account getMobileByCode(String mobileCode);

}
