package net.hwyz.iov.cloud.tsp.account.api.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 手机端账号二维码信息
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountQrcodeMp {

    /**
     * 二维码
     */
    private String qrcode;

}
