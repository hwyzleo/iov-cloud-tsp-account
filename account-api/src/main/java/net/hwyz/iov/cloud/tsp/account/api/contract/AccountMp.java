package net.hwyz.iov.cloud.tsp.account.api.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class AccountMp extends Account {

    /**
     * 昵称
     */
    private String nickname;
    /**
     * 签名简介
     */
    private String bio;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private String gender;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 用车城市
     */
    private String city;

}
