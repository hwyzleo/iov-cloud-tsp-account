package net.hwyz.iov.cloud.tsp.account.api.contract.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新客户端语言请求
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientLanguageRequest {

    /**
     * 客户端语言
     */
    private String language;

}
