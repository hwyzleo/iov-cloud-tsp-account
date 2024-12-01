package net.hwyz.iov.cloud.tsp.account.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.bean.MptAccount;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountMpt;

/**
 * 账号相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface AccountMptApi {

    /**
     * 分页查询账号信息
     *
     * @param account    账号信息
     * @param mptAccount 后台管理用户
     * @return 账号信息列表
     */
    TableDataInfo list(AccountMpt account, MptAccount mptAccount);

    /**
     * 导出账号信息
     *
     * @param response   响应
     * @param account    账号信息
     * @param mptAccount 后台管理用户
     */
    void export(HttpServletResponse response, AccountMpt account, MptAccount mptAccount);

}
