package net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.assembler;

import net.hwyz.iov.cloud.tsp.account.service.domain.token.model.TokenDo;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.TokenPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 令牌数据对象转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface TokenPoAssembler {

    TokenPoAssembler INSTANCE = Mappers.getMapper(TokenPoAssembler.class);

    /**
     * 领域对象转数据对象
     *
     * @param tokenDo 领域对象
     * @return 数据对象
     */
    @Mappings({})
    TokenPo fromDo(TokenDo tokenDo);

    /**
     * 数据对象转领域对象
     *
     * @param tokenPo 数据对象
     * @return 领域对象
     */
    @Mappings({})
    TokenDo toDo(TokenPo tokenPo);

}
