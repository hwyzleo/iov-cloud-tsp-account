package net.hwyz.iov.cloud.tsp.account.api.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.hwyz.iov.cloud.framework.common.annotation.Excel;

/**
 * 账号信息
 *
 * @author hwyz_leo
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    /**
     * 国家与地区代码
     */
    private String countryRegionCode;
    /**
     * 手机号
     */
    @Excel(name = "手机号", cellType = Excel.ColumnType.STRING)
    private String mobile;

}
