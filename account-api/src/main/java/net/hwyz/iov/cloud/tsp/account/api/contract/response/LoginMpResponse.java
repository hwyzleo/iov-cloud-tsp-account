package net.hwyz.iov.cloud.tsp.account.api.contract.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 手机端登录响应
 *
 * @author hwyz_leo
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LoginMpResponse extends LoginResponse {

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;

}
