package net.hwyz.iov.cloud.tsp.account.service.facade.assembler;

import net.hwyz.iov.cloud.tsp.account.api.contract.TokenMpt;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.TokenPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 管理后台令牌信息转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface TokenMptAssembler {

    TokenMptAssembler INSTANCE = Mappers.getMapper(TokenMptAssembler.class);

    /**
     * 数据对象转数据传输对象
     *
     * @param tokenPo 数据对象
     * @return 数据传输对象
     */
    @Mappings({})
    TokenMpt fromPo(TokenPo tokenPo);

    /**
     * 数据对象列表转数据传输对象列表
     *
     * @param tokenPoList 数据对象列表
     * @return 数据传输对象列表
     */
    List<TokenMpt> fromPoList(List<TokenPo> tokenPoList);

}
