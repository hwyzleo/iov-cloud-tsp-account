package net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.AccountPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 账号数据 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2023-06-15
 */
@Mapper
public interface AccountDao extends BaseDao<AccountPo, Long> {

    /**
     * 根据账号ID查询
     *
     * @param accountId 账号ID
     * @return 账号数据
     */
    AccountPo selectPoByAccountId(String accountId);

}
