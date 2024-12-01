package net.hwyz.iov.cloud.tsp.account.api.contract;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.annotation.Excel;
import net.hwyz.iov.cloud.framework.common.enums.Os;
import net.hwyz.iov.cloud.framework.common.web.domain.BaseRequest;

import java.util.Date;

/**
 * 管理后台账号信息
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientMpt extends BaseRequest {

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
     * 推送注册ID
     */
    private String pushRegId;

    /**
     * 客户端类型
     */
    private String clientType;

    /**
     * 操作系统
     */
    private Os os;

    /**
     * 最后登录IP
     */
    private String ip;

    /**
     * 最后登录时间
     */
    private Date loginTime;

}
