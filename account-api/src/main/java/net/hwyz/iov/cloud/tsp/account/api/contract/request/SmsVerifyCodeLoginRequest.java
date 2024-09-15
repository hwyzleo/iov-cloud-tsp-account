package net.hwyz.iov.cloud.tsp.account.api.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 短信验证码登录请求
 *
 * @author hwyz_leo
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SmsVerifyCodeLoginRequest extends SendSmsLoginVerifyCodeRequest {

    /**
     * 验证码
     */
    @NotBlank
    private String verifyCode;

}
