package net.hwyz.iov.cloud.tsp.account.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.bean.MptAccount;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountMpt;
import net.hwyz.iov.cloud.tsp.account.api.contract.ClientMpt;

/**
 * 客户端相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface ClientMptApi {

    /**
     * 分页查询客户端信息
     *
     * @param client     客户端信息
     * @param mptAccount 后台管理用户
     * @return 客户端信息列表
     */
    TableDataInfo list(ClientMpt client, MptAccount mptAccount);

    /**
     * 导出客户端信息
     *
     * @param response   响应
     * @param client     客户端信息
     * @param mptAccount 后台管理用户
     */
    void export(HttpServletResponse response, ClientMpt client, MptAccount mptAccount);

}
