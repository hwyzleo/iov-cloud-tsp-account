package net.hwyz.iov.cloud.tsp.account.api.feign.service;

import net.hwyz.iov.cloud.tsp.account.api.contract.Account;

/**
 * 账号相关服务接口
 *
 * @author hwyz_leo
 */
public interface AccountServiceApi {

    /**
     * 获取账号信息
     *
     * @param accountId 账号ID
     * @return 账号信息
     */
    Account getAccountInfo(String accountId);

}
