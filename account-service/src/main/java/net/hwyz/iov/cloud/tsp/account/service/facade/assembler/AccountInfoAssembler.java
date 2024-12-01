package net.hwyz.iov.cloud.tsp.account.service.facade.assembler;

import net.hwyz.iov.cloud.tsp.account.api.contract.Account;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.model.AccountDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 账号信息转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface AccountInfoAssembler {

    AccountInfoAssembler INSTANCE = Mappers.getMapper(AccountInfoAssembler.class);

    /**
     * 领域对象转数据传输对象
     *
     * @param accountDo 领域对象
     * @return 数据传输对象
     */
    @Mappings({
            @Mapping(target = "countryRegionCode", source = "countryRegion.code")
    })
    Account fromDo(AccountDo accountDo);

}
