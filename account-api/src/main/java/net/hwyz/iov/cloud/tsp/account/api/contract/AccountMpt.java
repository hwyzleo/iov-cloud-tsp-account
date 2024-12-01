package net.hwyz.iov.cloud.tsp.account.api.contract;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.annotation.Excel;
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
public class AccountMpt extends BaseRequest {

    /**
     * 账号ID
     */
    @Excel(name = "账号ID", width = 25)
    private String accountId;
    /**
     * 国家与地区代码
     */
    private String countryRegionCode;
    /**
     * 手机号
     */
    @Excel(name = "手机号", cellType = Excel.ColumnType.STRING)
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private String gender;
    /**
     * 注册来源
     */
    private String regSource;
    /**
     * 是否启用
     */
    private Boolean enable;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 20, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
