package net.hwyz.iov.cloud.tsp.account.api.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 发送短信登录验证码请求
 *
 * @author hwyz_leo
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SendSmsLoginVerifyCodeRequest {

    /**
     * 国家或地区代码
     */
    @NotBlank
    private String countryRegionCode;
    /**
     * 手机号
     */
    @NotBlank
    private String mobile;

}
