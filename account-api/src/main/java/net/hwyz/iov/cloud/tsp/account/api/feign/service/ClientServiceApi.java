package net.hwyz.iov.cloud.tsp.account.api.feign.service;

import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;

/**
 * 客户端相关服务接口
 *
 * @author hwyz_leo
 */
public interface ClientServiceApi {

    /**
     * 获取账号最新手机客户端
     *
     * @param accountId 账号ID
     * @return 客户端信息
     */
    ClientResponse getMpClient(String accountId);

}
