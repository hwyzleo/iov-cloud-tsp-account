package net.hwyz.iov.cloud.tsp.account.api.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 手机端账号信息
 *
 * @author hwyz_leo
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {

    /**
     * 国家与地区代码
     */
    private String countryRegionCode;
    /**
     * 手机号
     */
    private String mobile;

}
