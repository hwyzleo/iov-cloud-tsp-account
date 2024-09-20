package net.hwyz.iov.cloud.tsp.account.api.contract.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新客户端配置请求
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientConfigRequest {

    /**
     * 推送注册ID
     */
    private String pushRegId;

    /**
     * 操作系统
     */
    private String os;

}
