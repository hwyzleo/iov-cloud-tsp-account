package net.hwyz.iov.cloud.tsp.account.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.bean.MptAccount;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.tsp.account.api.contract.ClientMpt;
import net.hwyz.iov.cloud.tsp.account.api.contract.TokenMpt;

/**
 * 令牌相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface TokenMptApi {

    /**
     * 分页查询令牌信息
     *
     * @param token      令牌信息
     * @param mptAccount 后台管理用户
     * @return 令牌信息列表
     */
    TableDataInfo list(TokenMpt token, MptAccount mptAccount);

    /**
     * 导出令牌信息
     *
     * @param response   响应
     * @param token      令牌信息
     * @param mptAccount 后台管理用户
     */
    void export(HttpServletResponse response, TokenMpt token, MptAccount mptAccount);

}
