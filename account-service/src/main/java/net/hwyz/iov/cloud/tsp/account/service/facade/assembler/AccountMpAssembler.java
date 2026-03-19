package net.hwyz.iov.cloud.tsp.account.service.facade.assembler;

import net.hwyz.iov.cloud.tsp.account.api.contract.AccountMp;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.model.AccountDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 手机端账号信息转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface AccountMpAssembler {

    AccountMpAssembler INSTANCE = Mappers.getMapper(AccountMpAssembler.class);

    /**
     * 领域对象转数据传输对象
     *
     * @param accountDo 领域对象
     * @return 数据传输对象
     */
    @Mappings({
            @Mapping(target = "gender", expression = "java(accountDo.getGender().name())")
    })
    AccountMp fromDo(AccountDo accountDo);

}
