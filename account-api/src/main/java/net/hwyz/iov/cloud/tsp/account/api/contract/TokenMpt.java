package net.hwyz.iov.cloud.tsp.account.api.contract;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.annotation.Excel;
import net.hwyz.iov.cloud.framework.common.web.domain.BaseRequest;

import java.util.Date;

/**
 * 管理后台令牌信息
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TokenMpt extends BaseRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 账号唯一ID
     */
    @Excel(name = "账号ID", width = 25)
    private String accountId;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 权限范围
     */
    private String scope;

    /**
     * 客户端类型
     */
    private String clientType;

    /**
     * 发行时间
     */
    private Date issueTime;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 访问令牌过期时间
     */
    private Date accessTokenExpires;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 刷新令牌过期时间
     */
    private Date refreshTokenExpires;

}
