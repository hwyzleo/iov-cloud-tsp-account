package net.hwyz.iov.cloud.tsp.account.service.facade.assembler;

import net.hwyz.iov.cloud.tsp.account.api.contract.ClientMpt;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po.ClientPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 管理后台客户端信息转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface ClientMptAssembler {

    ClientMptAssembler INSTANCE = Mappers.getMapper(ClientMptAssembler.class);

    /**
     * 数据对象转数据传输对象
     *
     * @param clientPo 数据对象
     * @return 数据传输对象
     */
    @Mappings({})
    ClientMpt fromPo(ClientPo clientPo);

    /**
     * 数据对象列表转数据传输对象列表
     *
     * @param clientPoList 数据对象列表
     * @return 数据传输对象列表
     */
    List<ClientMpt> fromPoList(List<ClientPo> clientPoList);

}
