package net.hwyz.iov.cloud.tsp.account.service.domain.account.model;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.hwyz.iov.cloud.framework.common.domain.BaseDo;
import net.hwyz.iov.cloud.framework.common.enums.CountryRegion;
import net.hwyz.iov.cloud.framework.common.enums.Gender;
import net.hwyz.iov.cloud.tsp.account.service.domain.contract.enums.RegSource;

/**
 * 账号领域对象
 *
 * @author hwyz_leo
 */
@Getter
@SuperBuilder
public class AccountDo extends BaseDo<Long> {

    /**
     * 账号唯一ID
     */
    private String accountId;
    /**
     * 国家或地区代码
     */
    private CountryRegion countryRegion;
    /**
     * 手机号
     */
    private String mobile;
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
    private Gender gender;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 用车城市
     */
    private String city;
    /**
     * 注册来源
     */
    private RegSource regSource;
    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 初始化
     */
    public void init() {
        accountId = IdUtil.nanoId();
        gender = Gender.UNKNOWN;
        nickname = "用户" + RandomUtil.randomNumbers(8);
        enable = true;
        stateInit();
    }

    /**
     * 标记注册来源
     *
     * @param regSource 注册来源
     */
    public void markRegSource(RegSource regSource) {
        this.regSource = regSource;
    }

    public void modifyAvatar(String avatar) {
        this.avatar = avatar;
        stateChange();
    }

    /**
     * 修改昵称
     * @param nickname 昵称
     */
    public void modifyNickname(String nickname) {
        if (this.nickname == null || !this.nickname.equals(nickname)) {
            this.nickname = nickname;
            stateChange();
        }
    }

    /**
     * 修改签名简介
     * @param bio 签名简介
     */
    public void modifyBio(String bio) {
        if (this.bio == null || !this.bio.equals(bio)) {
            this.bio = bio;
            stateChange();
        }
    }

    /**
     * 修改性别
     * @param gender 性别
     */
    public void modifyGender(Gender gender) {
        if (this.gender == null || !this.gender.equals(gender)) {
            this.gender = gender;
            stateChange();
        }
    }

    /**
     * 修改生日
     * @param birthday 生日
     */
    public void modifyBirthday(String birthday) {
        if (this.birthday == null || !this.birthday.equals(birthday)) {
            this.birthday = birthday;
            stateChange();
        }
    }

    /**
     * 修改用车城市
     * @param city 用车城市
     */
    public void modifyCity(String city) {
        if (this.city == null || !this.city.equals(city)) {
            this.city = city;
            stateChange();
        }
    }

}
