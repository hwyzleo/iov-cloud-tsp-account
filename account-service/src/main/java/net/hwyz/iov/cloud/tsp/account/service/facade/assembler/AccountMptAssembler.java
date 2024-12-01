package net.hwyz.iov.cloud.tsp.account.service.facade.assembler;

import net.hwyz.iov.cloud.tsp.account.api.contract.AccountMpt;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.AccountPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 管理后台账号信息转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface AccountMptAssembler {

    AccountMptAssembler INSTANCE = Mappers.getMapper(AccountMptAssembler.class);

    /**
     * 数据对象转数据传输对象
     *
     * @param accountPo 数据对象
     * @return 数据传输对象
     */
    @Mappings({})
    AccountMpt fromPo(AccountPo accountPo);

    /**
     * 数据对象列表转数据传输对象列表
     *
     * @param accountPoList 数据对象列表
     * @return 数据传输对象列表
     */
    List<AccountMpt> fromPoList(List<AccountPo> accountPoList);

}
