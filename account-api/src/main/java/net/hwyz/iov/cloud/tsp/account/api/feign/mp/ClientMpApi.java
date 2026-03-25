package net.hwyz.iov.cloud.tsp.account.api.feign.mp;

import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.UpdateClientConfigRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.UpdateClientLanguageRequest;

/**
 * 客户端相关手机接口
 *
 * @author hwyz_leo
 */
public interface ClientMpApi {

    /**
     * 更新客户端配置
     *
     * @param clientId 客户端ID
     * @param request  更新客户端配置请求
     * @return 请求结果
     */
    Response<Void> updateClientConfig(String clientId, UpdateClientConfigRequest request);

    /**
     * 更新客户端语言
     *
     * @param clientId 客户端ID
     * @param request  更新客户端语言请求
     * @return 请求结果
     */
    Response<Void> updateClientLanguage(String clientId, UpdateClientLanguageRequest request);

}
