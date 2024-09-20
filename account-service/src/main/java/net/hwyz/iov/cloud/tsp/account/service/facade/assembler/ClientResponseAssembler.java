package net.hwyz.iov.cloud.tsp.account.service.facade.assembler;

import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.model.ClientDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 客户端响应转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface ClientResponseAssembler {

    ClientResponseAssembler INSTANCE = Mappers.getMapper(ClientResponseAssembler.class);

    /**
     * 数据对象转数据传输对象
     *
     * @param clientDo 领域对象
     * @return 数据传输对象
     */
    @Mappings({})
    ClientResponse fromDo(ClientDo clientDo);

}
