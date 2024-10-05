package net.hwyz.iov.cloud.tsp.account.api.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信手机码登录请求
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMobileCodeLoginRequest {

    /**
     * 手机号授权码
     */
    @NotBlank
    private String mobileCode;

}
