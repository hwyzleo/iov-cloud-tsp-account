package net.hwyz.iov.cloud.tsp.account.api.contract.response;

import lombok.*;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.Os;

/**
 * 客户端返回
 *
 * @author hwyz_leo
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    /**
     * 账号唯一ID
     */
    private String accountId;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 推送注册ID
     */
    private String pushRegId;

    /**
     * 操作系统
     */
    private Os os;

}
