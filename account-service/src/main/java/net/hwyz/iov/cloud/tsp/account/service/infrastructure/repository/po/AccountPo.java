package net.hwyz.iov.cloud.tsp.account.service.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.hwyz.iov.cloud.framework.mysql.po.BasePo;

/**
 * <p>
 * 账号数据 数据对象
 * </p>
 *
 * @author hwyz_leo
 * @since 2023-06-15
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_account")
public class AccountPo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号唯一ID
     */
    @TableField("account_id")
    private String accountId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码哈希值
     */
    @TableField("password")
    private String password;

    /**
     * 手机所属国家或地区
     */
    @TableField("country_region_code")
    private String countryRegionCode;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 签名简介
     */
    @TableField("bio")
    private String bio;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 生日
     */
    @TableField("birthday")
    private String birthday;

    /**
     * 用车城市
     */
    @TableField("city")
    private String city;

    /**
     * 注册来源
     */
    @TableField("reg_source")
    private String regSource;

    /**
     * 是否启用
     */
    @TableField("enable")
    private Boolean enable;
}
